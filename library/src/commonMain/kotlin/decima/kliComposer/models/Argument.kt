package decima.kliComposer.models

import decima.kliComposer.core.scopes.NullableParserDelegator

// TODO: override equals and hashcode in order for two instances to be equal if their name is equal
data class Argument<T>(
    val name: String,
    val description: String,
    val delegate: NullableParserDelegator<T>,
)
