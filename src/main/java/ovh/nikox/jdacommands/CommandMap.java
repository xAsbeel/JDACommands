package ovh.nikox.jdacommands;

import java.util.Map;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import org.jetbrains.annotations.NotNull;
import ovh.nikox.jdacommands.type.DiscordCommand;

public interface CommandMap {

  /**
   * Update all command entries on application scope.
   * Commands will persist through bot restarts.
   * **WARNING**: This method will delete all commands that are not registered in the command map.
   * @see #updateGlobal()
   */
  void updateGlobal();

  /**
   * Update all command entries on specified guild.
   * Commands will persist through bot restart.
   * **WARNING**: This method will delete all commands that are not registered in the command map.
   * @param guild The guild to update the commands on.
   * @see #updateGuild(Guild)
   */
  void updateGuild(@NotNull Guild guild);

  /**
   * Upsert all command entries on application scope.
   * Commands will persist through bot restart.
   * This method will only add new commands and update existing ones.
   * @see #updateGlobal()
   */
  void upsertGlobal();

  /**
   * Upsert all command entries on specified guild.
   * Commands will persist through bot restart.
   * This method will only add new commands and update existing ones.
   * @param guild The guild to upsert the commands on.
   * @see #updateGuild(Guild)
   */
  void upsertGuild(@NotNull Guild guild);

  /**
   * Register a command in the command map.
   * This method will not update the commands on the Discord api.
   * @param command The command to register.
   */
  void registerCommand(@NotNull DiscordCommand command);

  /**
   * Unregister the command from the command map.
   * If command is not registered, this method will do nothing.
   * @param command The command to unregister.
   */
  void unregisterCommand(@NotNull DiscordCommand command);

  /**
   * Unregister the command from the command map.
   * If command is not registered, this method will do nothing.
   * @param commandName The name of the command to unregister.
   */
  void unregisterCommand(@NotNull String commandName);

  /**
   * Get the immutable copy of the command map.
   * @return The command map.
   */
  @NotNull
  Map<String, DiscordCommand> getCommandMap();

  /**
   * Create a new {@link SlashCommandMap} instance.
   * @param jda The JDA instance to use.
   * @return A new {@link SlashCommandMap} instance.
   */
  static SlashCommandMap slash(@NotNull JDA jda) {
    return new SlashCommandMap(jda);
  }

}
