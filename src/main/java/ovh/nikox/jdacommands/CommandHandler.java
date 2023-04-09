package ovh.nikox.jdacommands;

import java.util.Objects;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import ovh.nikox.jdacommands.type.SlashCommand;

class CommandHandler extends ListenerAdapter {

  private final CommandMap commandMap;

  protected CommandHandler(@NotNull CommandMap commandMap) {
    Objects.requireNonNull(commandMap);
    if (!(commandMap instanceof SlashCommandMap)) {
      throw new IllegalArgumentException("CommandMap must be an instance of SlashCommandMap");
    }

    this.commandMap = commandMap;
  }

  @Override
  public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
    String name = event.getName().toLowerCase();
    SlashCommand command = (SlashCommand) this.commandMap.getCommandMap().get(name);
    if (command == null) {
      return;
    }

    command.call(event);
  }
}
