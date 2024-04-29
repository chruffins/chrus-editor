import kotlinx.serialization.json.Json
import java.io.FileNotFoundException
import kotlin.io.path.Path
import kotlin.io.path.exists

class SceneReader(private val filename: String) {
    init {
        if (!Path(filename).exists()) {
            throw FileNotFoundException()
        }
    }

    fun readFile(): List<ChrusNode<ChrusData>> {
        val nodes: List<ChrusNode<ChrusData>> = mutableListOf()
        // nodes.plus(ChrusNode(5, 0, "helloworld", listOf(),ChrusScript("helloworld.lua"))



        return listOf()
    }
}