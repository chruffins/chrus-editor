import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun main() = application {
    val testNode: ChrusNode<ChrusScript> = ChrusNode(
        1,
        1000L,
        "script",
        ChrusScript("data/helloworld.lua")
    )
    val encodedTestNode: String = Json.encodeToString(testNode)
    val retestNode: ChrusNode<ChrusData> = Json.decodeFromString(encodedTestNode)
    println(Json.encodeToString(retestNode))


    Window(onCloseRequest = ::exitApplication, title = "Chrus Editor") {
        App()
    }
}