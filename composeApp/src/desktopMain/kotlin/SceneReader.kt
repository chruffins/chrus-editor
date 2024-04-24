import kotlinx.serialization.json.Json
import java.io.FileNotFoundException
import kotlin.io.path.Path
import kotlin.io.path.exists

class SceneReader public constructor(val filename: String) {
    private val fn: String = filename;

    init {
        if (!Path(fn).exists()) {
            throw FileNotFoundException();
        }
    }


}