package decima.kliComposer.models

import decima.kliComposer.core.scopes.NullableParserDelegator

data class Option<T>(
    val name: String,
    val description: String,
    val delegate: NullableParserDelegator<T>,
)
