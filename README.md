
A Kotlin DSL wrapping Mojang's Brigadier builder.  See [BrigadierDSL.kt](src/main/kotlin/BrigadierDSL.kt).

Example Usage:
```kotlin
import btpos.dsl.brigadier.Command

// Start the command tree
Command.literal<CommandSourceStack>("find") {
    // Add a literal. Command at this point is "/find my"
    "my" {
        // Add a "string" argument with the name "item_arg"
        "item_arg"(StringArgumentType.string()) {
            requires { it.isPlayer }
            // Add suggestions. The ! operator adds a literal, and the invoke adds a literal with a tooltip.
            suggests { ctx ->
                !"ball"
                !1
                "car" { "This will be a tooltip!" }
                2 { "This will be another tooltip!" }
                withOffset(5) {
                    "future" { "Using knowledge of later words!" }
                }
            }
            executes { ctx ->
                val toFind = ctx.optionalArgument<String>("item_arg")
                    ?: return@executes ctx.sendFailure("You didn't ask for an item!")
                
                if (toFind == "car")
                    return@executes ctx.sendSuccess("yay!")
                
                return@executes 0
            }
            "here" {
                executes(::myCommand)
            }
		        // If you have another function that produces a LiteralArgumentBuilder or a previously-compiled CommandNode, add it with +
            +makeSubCommand()
        }
    }
}
 ```

More macros are available in [MCSpecificMacros.kt](MCSpecificMacros.kt) for working with Minecraft specifically.  Just pop the file in your project yourself if you want to use them; I couldn't be bothered to get a standalone MC build working here.

## Add as a Dependency

### Gradle:

Kotlin:
```kotlin
repositories {
    mavenCentral()
    maven(url="https://jitpack.io")
}

dependencies {
    implementation("btpos.dsl.brigadier:BrigadierDSL:1.0.0")
}
```