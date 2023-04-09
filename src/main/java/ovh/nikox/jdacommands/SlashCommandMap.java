package ovh.nikox.jdacommands;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import org.jetbrains.annotations.NotNull;
import ovh.nikox.jdacommands.type.DiscordCommand;
import ovh.nikox.jdacommands.type.SlashCommand;

/**
 * A command map that stores slash commands.
 * @see CommandMap
 * @see CommandMap#slash(JDA)
 */
public class SlashCommandMap implements CommandMap {

  private final JDA jda;
  private final Map<String, SlashCommand> commandMap;

  protected SlashCommandMap(@NotNull JDA jda) {
    this.jda = Objects.requireNonNull(jda);
    this.commandMap = new HashMap<>();

    jda.addEventListener(new CommandHandler(this));
  }

  @Override
  public void updateGlobal() {
    this.jda.updateCommands().addCommands(
        this.commandMap
            .values()
            .stream()
            .map(SlashCommand::getCommandData)
            .collect(Collectors.toList())
    ).queue();
  }

  @Override
  public void updateGuild(@NotNull Guild guild) {
    Objects.requireNonNull(guild);

    guild.updateCommands().addCommands(
        this.commandMap
            .values()
            .stream()
            .map(SlashCommand::getCommandData)
            .collect(Collectors.toList())
    ).queue();
  }

  @Override
  public void upsertGlobal() {
    this.commandMap
        .values()
        .stream()
        .map(SlashCommand::getCommandData)
        .forEach(command -> this.jda.upsertCommand(command).queue());
  }

  @Override
  public void upsertGuild(@NotNull Guild guild) {
    Objects.requireNonNull(guild);

    this.commandMap
        .values()
        .stream()
        .map(SlashCommand::getCommandData)
        .forEach(command -> guild.upsertCommand(command).queue());
  }

  @Override
  public void registerCommand(@NotNull DiscordCommand command) {
    Objects.requireNonNull(command);

    if (!(command instanceof SlashCommand)) {
      throw new IllegalArgumentException("Command must be a SlashCommand");
    }

    SlashCommand slashCommand = (SlashCommand) command;
    this.commandMap.put(slashCommand.getCommandData().getName().toLowerCase(), slashCommand);
  }

  @Override
  public void unregisterCommand(@NotNull DiscordCommand command) {
    Objects.requireNonNull(command);

    if (!(command instanceof SlashCommand)) {
      throw new IllegalArgumentException("Command must be a SlashCommand");
    }

    SlashCommand slashCommand = (SlashCommand) command;
    this.unregisterCommand(slashCommand.getCommandData().getName());
  }

  @Override
  public void unregisterCommand(@NotNull String commandName) {
    Objects.requireNonNull(commandName);

    this.commandMap.remove(commandName.toLowerCase());
  }

  @Override
  public @NotNull Map<String, DiscordCommand> getCommandMap() {
    return Collections.unmodifiableMap(this.commandMap);
  }


}
