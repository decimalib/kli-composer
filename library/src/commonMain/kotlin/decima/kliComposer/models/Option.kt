package decima.kliComposer.models

import decima.kliComposer.core.delegators.NullableDelegator

data class Option<T>(
    val name: String,
    val description: String,
    val delegate: NullableDelegator<T>,
)
