package decima.kliComposer.core.scopes

import decima.kliComposer.models.Argument
import decima.kliComposer.models.Command
import decima.kliComposer.models.Flag
import decima.kliComposer.models.Option
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class CommandScope {
    private val args = mutableSetOf<Argument<*>>()
    private val options = mutableSetOf<Option<*>>()
    private val flags = mutableSetOf<Flag>()
    private var execute: () -> Unit = {}

    fun <T> argument(
        name: String,
        description: String = "",
    ): NullableParserDelegator<T> {
        val argDelegate = NullableParserDelegator<T>()
        args.add(Argument(name, description, argDelegate))
        return argDelegate
    }

    fun flag(
        name: String,
        description: String,
    ): ParserDelegator<Boolean> {
        val flagDelegate = ParserDelegator(false)
        flags.add(Flag(name, description, flagDelegate))
        return flagDelegate
    }

    fun <T> option(
        name: String,
        description: String,
    ): NullableParserDelegator<T> {
        val optionDelegate = NullableParserDelegator<T>()
        options.add(Option(name, description, optionDelegate))
        return NullableParserDelegator()
    }

    fun execute(block: () -> Unit) {
        execute = block
    }

    fun build(
        name: String,
        desc: String,
    ): Command {
        return Command(name, desc, args.toList(), options.toList(), flags.toList(), execute)
    }
}

class NullableParserDelegator<T>(var currValue: T? = null) : ReadWriteProperty<Any?, T?> {
    override fun getValue(
        thisRef: Any?,
        property: KProperty<*>,
    ): T? {
        return currValue
    }

    override fun setValue(
        thisRef: Any?,
        property: KProperty<*>,
        value: T?,
    ) {
        currValue = value
    }
}

// TODO: Should only parse primitive types and strings
class ParserDelegator<T>(var currValue: T) : ReadWriteProperty<Any?, T> {
    override fun getValue(
        thisRef: Any?,
        property: KProperty<*>,
    ): T {
        return currValue
    }

    override fun setValue(
        thisRef: Any?,
        property: KProperty<*>,
        value: T,
    ) {
        currValue = value
    }
}
