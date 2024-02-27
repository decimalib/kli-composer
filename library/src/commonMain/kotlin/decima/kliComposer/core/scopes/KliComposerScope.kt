package decima.kliComposer.core.scopes

import decima.kliComposer.core.builder.KliComposer
import decima.kliComposer.models.Command

class KliComposerScope {
    private val commands = mutableSetOf<Command>()

    fun build(): KliComposer = KliComposer(commands.toList())

    fun command(
        name: String,
        description: String = "",
        block: CommandScope.() -> Unit,
    ) {
        val scope = CommandScope()
        scope.block()
        commands.add(scope.build(name, description))
    }
}
