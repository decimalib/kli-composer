package decima.kliComposer.models

data class Command(
    val name: String,
    val description: String,
    val args: List<Argument<Any>>,
    val options: List<Option<Any>>,
    val flags: List<Flag>,
    val execute: () -> Unit,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other is Command && this.name == other.name) return true
        return false
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}
