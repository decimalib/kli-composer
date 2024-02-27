# Kli-Composer <img src="./docs/images/kli-composer-logo.png" width="140" height="140" align="right" />

Kli-Composer is a DSL for defining the building blocks of your next command-line interface (CLI) in Kotlin
Multiplatform.

> [!IMPORTANT]
> This project is still in the early stages of development and is not yet ready for use.

## Future Intended Usage

### Defining the CLI

```kotlin
val kli = kliComposer {
    command("run", "Run the application") {
        val debug: Boolean by flag("--debug", "Enable debug mode")
        val fileName: String by argument("input", "Input file name")
        val iterations: Int? by option("--iter", "Number of iterations")
        execute {
            println("Running application")
            val optionalValue = value ?: "not set"
            if (debug) println("Debug mode enabled for file: $fileName, with $iterations iterations")
            else println("Debug mode disabled for file: $fileName")
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

When calling the `run` method, the CLI will parse the command-line arguments,
flags and options and execute the appropriate command as registered.

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
<td> <strong> Command </strong> </td> <td> <strong> Output </strong> </td>
</tr>
<tr>
<td>

```bash
$ ./kli run --debug input.txt --iter=10
```

</td>
<td>

```
Running application
Debug mode enabled for file: input.txt, with 10 iterations
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
run [--debug] <input> [--iter=?]
    desc: Run the application
    options:
        iter: Number of iterations
    args:
        input: Input file name
    flags:
        debug: Enable debug mode
test
    desc: Run tests
```

</tr>
</table>

> [!NOTE]
> If a description for a command, argument, flag or option is not provided, it will be omitted from the help output.
