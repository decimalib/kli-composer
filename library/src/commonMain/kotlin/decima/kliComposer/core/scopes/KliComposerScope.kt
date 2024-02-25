package decima.kliComposer.core.scopes

import decima.kliComposer.models.Command
import decima.kliComposer.models.KliComposer

class KliComposerScope {
    private val commands = mutableSetOf<Command>()

    fun command(
        name: String,
        description: String = "",
        block: CommandScope.() -> Unit,
    ) {
        val scope = CommandScope()
        scope.block()
        commands.add(scope.build(name, description))
    }

    // TODO: make an abstract class that has a build function
    fun build(): KliComposer {
        return KliComposer(commands.toList())
    }
}
