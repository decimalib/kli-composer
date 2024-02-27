package decima.kliComposer.core.delegators

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class LateinitDelegator<T : Any> : ReadWriteProperty<Any?, T> {
    internal lateinit var currValue: T

    override fun getValue(
        thisRef: Any?,
        property: KProperty<*>,
    ): T = currValue

    override fun setValue(
        thisRef: Any?,
        property: KProperty<*>,
        value: T,
    ) {
        currValue = value
    }
}
