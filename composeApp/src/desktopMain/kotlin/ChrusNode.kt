import kotlinx.serialization.Serializable

@Serializable(with = ChrusSerializer::class)
class ChrusNode<T: ChrusData>(
    val type: Int, var id: Long, var name: String,
    val nodeData: T
) {

}