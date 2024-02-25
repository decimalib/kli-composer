package decima.kliComposer.models

import decima.kliComposer.core.scopes.ParserDelegator

data class Flag(
    val name: String,
    val description: String,
    val delegate: ParserDelegator<Boolean>,
)
