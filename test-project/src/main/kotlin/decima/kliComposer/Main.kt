package decima.kliComposer

import kliComposer

fun main() {
    // TODO("add option to build commands outside of kliComposerScope and concatenate them after")
    kliComposer {
        command("run", "Run the application") {
            val debug: Boolean by flag("--debug", "Enable debug mode")
            val fileName: String by argument("input", "Input file name")
            val value: Int? by option("--value", "Value to set")
            execute {
                println("Running application")
                val optionalValue = value ?: "not set"
                if (debug) {
                    println("Debug mode enabled for file: $fileName with value: $optionalValue")
                } else {
                    println("Debug mode disabled for file: $fileName with value: $value")
                }
            }
        }
    }.run()
}
