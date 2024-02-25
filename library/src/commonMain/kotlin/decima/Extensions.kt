import decima.kliComposer.core.scopes.KliComposerScope
import decima.kliComposer.models.KliComposer

fun kliComposer(block: KliComposerScope.() -> Unit): KliComposer {
    val scope = KliComposerScope()
    scope.block()
    return scope.build()
}
