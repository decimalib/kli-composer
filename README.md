# Kli-Composer

Kli-Composer is a DSL for defining and running command-line interfaces (CLIs) for Kotlin Multiplatform projects. 

> [!IMPORTANT]
> This project is still in the early stages of development and is not yet ready for use.

## Future Intended Usage

### Defining the CLI

```kotlin
val kli: KliComposer = kliComposer {
        command("run") {
            description("Run the application")
            option("--debug") {
                description("Enable debug mode")
                execute {
                    println("Debug mode enabled")
                }
            }
            argument("input") {
                description("Input file")
            }
            execute {
                println("Running the application")
            }
        }
        command("test") {
            description("Run tests")
            execute {
                println("Running tests")
            }
        }
    }
```

### Running the CLI

```kotlin
fun main() {
    val kli = KliComposer { 
        // ...
    }
    kli.run()
}
```

```kotlin
fun main() {
    val kli = KliComposer.fromYaml("kli.yaml")
    kli.run()
}
```