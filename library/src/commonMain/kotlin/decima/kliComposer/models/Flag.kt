package decima.kliComposer.models

import decima.kliComposer.core.delegators.InitialValueDelegator

data class Flag(
    val name: String,
    val description: String,
    val delegate: InitialValueDelegator<Boolean>,
)
