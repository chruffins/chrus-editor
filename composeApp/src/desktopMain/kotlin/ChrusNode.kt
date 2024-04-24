import kotlinx.serialization.Serializable

class ChrusNode<T: ChrusData>(private val type: Int, private val id: Long, private val name: String,
                                      private val children: List<Long>, private val nodeData: T) {
    fun serialize(): String {
        return "Fuck! " + nodeData.serialize()
    }
}