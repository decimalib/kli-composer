# Kli-Composer

> Kli-Composer is a DSL for defining the building blocks of your next command-line interface (CLI) in Kotlin Multiplatform.

<p align="center">
    <img src="./docs/images/kli-composer-logo.jpeg" width="250">
</p>


> [!IMPORTANT]
> This project is still in the early stages of development and is not yet ready for use.

## Future Intended Usage

### Defining the CLI

```kotlin
val kli = kliComposer {
    command("run") {
        description("Run the application")
        option("--debug") {
            description("Enable debug mode")
            execute {
                println("Debug mode enabled")
            }
        }
        argument("input")
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

When calling the `run` method, the CLI will parse the command-line arguments and options and execute the appropriate command registered.

```kotlin
fun main() {
    val kli = kliComposer {
        //... 
    }
    kli.run()
}
```

<table>
<tr>
<td> Command </td> <td> Output </td>
</tr>
<tr>
<td>

```bash
$ ./kli run --debug input.txt
```

</td>
<td>

```
Debug mode enabled
Running the application
```

</td>
</tr>
<tr>
<td>

```bash
$ ./kli test
```

</td>
<td>

```
Running tests
```

</td>
<tr>
<td>

```bash
$ ./kli help
```

</td>
<td>

```
run [--debug] <input>
    desc: Run the application
    debug: Enable debug mode
test
    description: Run tests
```
</tr>
</table>

> [!NOTE]
> If a description for a command, argument or option is not provided, it will be omitted from the help output.
