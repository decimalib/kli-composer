package decima.kliComposer

import kliComposer

fun main() {
    val kli =
        kliComposer {
            command("run", "Run the application") {
                val debug: Boolean by flag("--debug", "Enable debug mode")
                // TODO: should not be nullable, since argument is required
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
        }
    println("Starting kli").also { kli.run() }
}
