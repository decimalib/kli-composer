package decima.kliComposer.models

import decima.kliComposer.core.delegators.LateinitDelegator

data class Argument<T : Any>(
    val name: String,
    val description: String,
    val delegator: LateinitDelegator<T>,
)
