package ovh.nikox.jdacommands.type;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.jetbrains.annotations.NotNull;

public interface SlashCommand extends DiscordCommand {

  default @NotNull CommandType getType() {
    return CommandType.SLASH;
  }

  void call(@NotNull SlashCommandInteractionEvent event);

  @NotNull
  SlashCommandData getCommandData();


}
