package decima.kliComposer.core.delegators

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class InitialValueDelegator<T : Any>(internal var currValue: T) : ReadWriteProperty<Any?, T> {
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
