package decima.kliComposer.core.delegators

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class NullableDelegator<T>(internal var currValue: T? = null) : ReadWriteProperty<Any?, T?> {
    override fun getValue(
        thisRef: Any?,
        property: KProperty<*>,
    ): T? = currValue

    override fun setValue(
        thisRef: Any?,
        property: KProperty<*>,
        value: T?,
    ) {
        currValue = value
    }
}
