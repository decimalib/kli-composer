package decima.kliComposer.models

data class KliComposer(
    val commands: List<Command>,
) {
    init {
        require(commands.isNotEmpty()) { "No commands found" }
    }

    /**
     * Runs the KLI application with the given commands
     */
    fun run() {
        // TODO: should call help at the beginning
        while (true) {
            val input = readln()
            val args = input.split(" ")
            val command = args.first()
            val commandArgs = args.drop(1)
            // find the command
            lateinit var cmd: Command
            try {
                cmd = commands.first { it.name == command }
            } catch (e: NoSuchElementException) {
                println("Command not found")
                // println(help())
                continue
            }
            val flagsReceived = mutableListOf<String>()
            val optionsReceived = mutableMapOf<String, String>()
            val argumentsReceived = mutableListOf<String>()
            commandArgs.forEach { arg ->
                when {
                    arg.startsWith("--") && arg.contains("=") -> {
                        // It's an option with value (e.g., --option=value)
                        val (option, value) = arg.split("=")
                        optionsReceived[option] = value
                    }

                    arg.startsWith("--") -> {
                        // It's a flag (e.g., --debug)
                        flagsReceived.add(arg)
                    }

                    else -> {
                        // It's a regular argument
                        argumentsReceived.add(arg)
                    }
                }
            }
            println("flagsReceived: $flagsReceived")
            println("optionsReceived: $optionsReceived")
            println("argumentsReceived: $argumentsReceived")
            // TODO: remove O2 complexity processing, and make it in parallel
            // TODO: use a logger
            for (flag in flagsReceived) {
                println("Setting flag $flag to true")
                cmd.flags.forEach {
                    println(it.name)
                    if (it.name == flag) {
                        println("Setting flag ${it.name} to true")
                        val value = it.delegate.getValue(null, it.delegate::currValue)
                        // toggle the flag
                        it.delegate.setValue(null, it.delegate::currValue, !value)
                    }
                }
            }
            cmd.execute()
        }
    }

    /**
     * Prints the help message for the KLI application
     */
    private fun help(): String {
        TODO()
    }
}
