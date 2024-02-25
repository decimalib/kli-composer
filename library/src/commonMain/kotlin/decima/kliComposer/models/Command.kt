package decima.kliComposer.models

data class Command(
    val name: String,
    val description: String,
    val args: List<Argument<*>>,
    val options: List<Option<*>>,
    val flags: List<Flag>,
    val execute: () -> Unit,
)

// TODO: reset command after finish
