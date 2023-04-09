# JDACommands
Simple command library for JDA. <br>
For now it only supports slash commands, support for context menus is planned.

## Dependency

In order to use this library you must also include JDA in your build.

### Maven (pom.xml)
```xml
  <repositories>
    <repository>
      <id>minecodes-repo</id>
      <url>https://repository.minecodes.pl/releases</url>
    </repository>
  </repositories>

  <dependencies>
    <dependency>
      <groupId>ovh.nikox.jdacommands</groupId>
      <artifactId>JDACommands</artifactId>
      <version>1.0.0</version>
    </dependency>

    <!-- JDA is required. -->
    <dependency>
      <groupId>net.dv8tion</groupId>
      <artifactId>JDA</artifactId>
      <version>YOUR-JDA-VERSION</version>
    </dependency>
  </dependencies>
```

### Gradle (build.gradle)
```gradle
repositories {
  mavenCentral()
  maven("https://repository.minecodes.pl/releases")
}

dependencies {
  implementation("ovh.nikox.jdacommands:JDACommands:1.0.0")
  implementation("net.dv8tion:JDA:VERSION")
}
```

## Usage

1. SlashCommands

Initializer class
```java
CommandMap map = CommandMap.slash(jdaInstance);

map.registerCommand(slashCommand);
map.updateGlobal();
```

Command class
```java
public class MyCommand implements SlashCommand {
  
  @Override
  public void call(@NotNull SlashCommandInteractionEvent event) {
    event.reply("It works!").queue();
  }
  
  @Override
  public SlashCommandData getCommandData() {
    return Commands.slash("hello", "Hello World!");
  }
```
