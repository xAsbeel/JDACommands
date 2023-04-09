package ovh.nikox.jdacommands.type;

import org.jetbrains.annotations.NotNull;

public interface DiscordCommand {

  @NotNull
  CommandType getType();

}
