# Kli-Composer

<p align="center">
    <img src="./docs/images/kli-composer-logo.jpeg" width="300">
</p>

Kli-Composer is a DSL for defining the building blocks of your next command-line interface (CLI) in Kotlin Multiplatform.

> [!IMPORTANT]
> This project is still in the early stages of development and is not yet ready for use.

## Future Intended Usage

### Defining the CLI

```kotlin
val kli = kliComposer {
    command("run", "Run the application") {
        val debug: Boolean by flag("--debug", "Enable debug mode")
        val inputFileName: String? by argument("input", "Input file")
        execute {
            println("Running application")
            if (debug) {
                println("Debug mode enabled for file: $inputFileName")
            } else {
                println("Debug mode disabled for file: $inputFileName")
            }
        }
    }
    command("test", "Run tests") {
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
Running application
Debug mode enabled for file: input.txt
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
    desc: Run tests
```
</tr>
</table>

> [!NOTE]
> If a description for a command, argument or option is not provided, it will be omitted from the help output.
