import decima.kliComposer.core.builder.KliComposer
import decima.kliComposer.core.scopes.KliComposerScope

fun kliComposer(block: KliComposerScope.() -> Unit): KliComposer {
    val scope = KliComposerScope()
    scope.block()
    return scope.build()
}
