package decima.kliComposer.core.scopes

import decima.kliComposer.core.delegators.InitialValueDelegator
import decima.kliComposer.core.delegators.LateinitDelegator
import decima.kliComposer.core.delegators.NullableDelegator
import decima.kliComposer.models.Argument
import decima.kliComposer.models.Command
import decima.kliComposer.models.Flag
import decima.kliComposer.models.Option

class CommandScope {
    private val args = mutableSetOf<Argument<Any>>()
    private val options = mutableSetOf<Option<Any>>()
    private val flags = mutableSetOf<Flag>()
    private var execute: () -> Unit = {}

    fun <T : Any> argument(
        name: String,
        description: String = "",
    ): LateinitDelegator<T> {
        val argDelegate = LateinitDelegator<T>()
        @Suppress("UNCHECKED_CAST")
        args.add(Argument(name, description, argDelegate) as Argument<Any>)
        return argDelegate
    }

    fun <T : Any> option(
        name: String,
        description: String,
    ): NullableDelegator<T> {
        val optionDelegate = NullableDelegator<T>()
        @Suppress("UNCHECKED_CAST")
        options.add(Option(name, description, optionDelegate) as Option<Any>)
        return optionDelegate
    }

    fun flag(
        name: String,
        description: String,
    ): InitialValueDelegator<Boolean> {
        val flagDelegate = InitialValueDelegator(false)
        flags.add(Flag(name, description, flagDelegate))
        return flagDelegate
    }

    fun execute(block: () -> Unit) {
        execute = block
    }

    fun build(
        name: String,
        desc: String,
    ): Command = Command(name, desc, args.toList(), options.toList(), flags.toList(), execute)
}
