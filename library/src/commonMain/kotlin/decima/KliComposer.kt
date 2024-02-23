package decima

fun main() {
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
            option("--coverage") {
                description("Generate test coverage report")
                execute {
                    println("Generating test coverage report")
                }
            }
        }
    }
    kli.run()
}

data class KliComposer(
    val commands: List<Command>
) {
    fun run() {
        // TODO: implement the command line parsing and execution
    }
}

data class Command(
    val name: String,
    val description: String,
    val options: List<Option>,
    val args: List<Argument>,
    val execute: () -> Unit
)

// TODO: override equals and hashcode in order for two instances to be equal if their name is equal
data class Argument(
    val name: String,
    val description: String,
    val execute: () -> Unit
)

data class Option(
    val name: String,
    val description: String,
    val execute: () -> Unit
)

class KliComposerScope {
    private val commands = mutableSetOf<Command>()

    fun command(name: String, block: CommandScope.() -> Unit) {
        val scope = CommandScope()
        scope.block()
        commands.add(scope.build(name))
    }

    fun build(): KliComposer {
        return KliComposer(commands.toList())
    }

}

class CommandScope {
    private val args = mutableSetOf<Argument>()
    private val options = mutableSetOf<Option>()
    private var desc: String = ""
    private var execute: () -> Unit = {}

    fun argument(name: String, block: ArgumentScope.() -> Unit) {
        val scope = ArgumentScope()
        scope.block()
        args.add(scope.build(name))
    }

    fun option(name: String, block: OptionScope.() -> Unit) {
        val scope = OptionScope()
        scope.block()
        options.add(scope.build(name))
    }

    fun description(description: String) {
        desc = description
    }

    fun execute(block: () -> Unit) {
        execute = block
    }

    fun build(name: String): Command {
        return Command(name, desc, options.toList(), args.toList(), execute)
    }

}

class OptionScope {
    private var desc: String = ""
    private var execute: () -> Unit = {}

    fun description(description: String) {
        desc = description
    }

    fun execute(block: () -> Unit) {
        execute = block
    }

    fun build(name: String): Option {
        return Option(name, desc, execute)
    }
}

class ArgumentScope {
    private var desc: String = ""
    private var execute: () -> Unit = {}

    fun description(description: String) {
        desc = description
    }

    fun execute(block: () -> Unit) {
        execute = block
    }

    fun build(name: String): Argument {
        return Argument(name, desc, execute)
    }
}

fun kliComposer(block: KliComposerScope.() -> Unit): KliComposer {
    val scope = KliComposerScope()
    scope.block()
    return scope.build()
}
