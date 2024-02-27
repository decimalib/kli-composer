package decima.kliComposer.core.builder

import decima.kliComposer.models.Command

// TODO: add exception handling if required args are not provided
private typealias ParsedCommandLine = Triple<List<String>, Map<String, String>, List<String>>

class KliComposer(
    private val commands: List<Command>,
) {
    init {
        require(commands.isNotEmpty()) { "No commands found" }
    }

    /**
     * Runs the KLI application with the given commands
     */
    fun run() {
        while (true) {
            // TODO: change readln to be suspending
            val userInput = readln()
            val tokens = userInput.split(" ")
            val command = tokens.first()
            val commandArgs = tokens.drop(1)
            lateinit var targetCommand: Command
            try {
                // TODO: substitute with single and remove var
                targetCommand = commands.first { it.name == command }
            } catch (e: NoSuchElementException) {
                println("Command not found")
                continue
            }
            println("Command found: $targetCommand")
            println("CMD ARGS: ${targetCommand.args}")
            println("CMD OPTIONS: ${targetCommand.options}")
            println("CMD FLAGS: ${targetCommand.flags}")
            // parse the command arguments
            val (parsedArgs, parsedOptions, parsedFlags) = parseCommandLine(commandArgs)
            // set the options and args, and toggle the flags, according to the parsed command line received
            for (flag in parsedFlags) {
                println("Setting flag $flag to true")
                targetCommand.flags.forEach {
                    if (it.name == flag) {
                        // TODO: organize this code
                        val currValue = it.delegate.currValue
                        it.delegate.setValue(null, it.delegate::currValue, !currValue)
                    }
                }
            }
            for ((option, value) in parsedOptions) {
                println("Setting option $option to $value")
                targetCommand.options.forEach {
                    if (it.name == option) {
                        // TODO: remove hardcoded value and implement with expect/actual in JVM,..
                        it.delegate.setValue(null, it.delegate::currValue, 3)
                    }
                }
            }
            for (arg in parsedArgs) {
                println("Setting arg $arg")
                targetCommand.args.forEach {
                    it.delegator.setValue(null, it.delegator::currValue, arg)
                }
            }
            targetCommand.execute()
        }
    }

    private fun parseCommandLine(tokens: List<String>): ParsedCommandLine {
        val flags = mutableListOf<String>()
        val options = mutableMapOf<String, String>()
        val args = mutableListOf<String>()
        tokens.forEach { arg ->
            when {
                arg.startsWith("--") && arg.contains("=") -> {
                    // It's an option with value (e.g., --option=value)
                    val (option, value) = arg.split("=")
                    options[option] = value
                }

                arg.startsWith("--") -> {
                    // It's a flag (e.g., --debug)
                    flags.add(arg)
                }

                else -> {
                    // It's a regular argument
                    args.add(arg)
                }
            }
        }
        println("Args: $args")
        println("Options: $options")
        println("Flags: $flags")
        return ParsedCommandLine(args, options, flags)
    }

    /**
     * Prints the help message for the KLI application
     */
    private fun help(): String {
        TODO("implement help")
    }
}
