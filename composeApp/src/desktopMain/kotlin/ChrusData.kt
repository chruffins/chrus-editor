import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure

sealed interface ChrusSerializable {
    fun serialize(): String
}

class ChrusSerializer<T : ChrusData>(private val dataSerializer: KSerializer<T>) : KSerializer<ChrusNode<T>> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("ChrusNode") {
        element<Int>("type")
        element<Long>("id")
        element<String>("name")
        element<ChrusData>("data")
    }

    private val dataDescriptor: SerialDescriptor = dataSerializer.descriptor
    override fun serialize(encoder: Encoder, value: ChrusNode<T>) {
        encoder.encodeStructure(descriptor) {
            encodeIntElement(descriptor, 0, value.type)
            encodeLongElement(descriptor, 1, value.id)
            encodeStringElement(descriptor, 2, value.name)
            encodeSerializableElement(descriptor, 3, dataSerializer, value.nodeData)
        }
    }
    override fun deserialize(decoder: Decoder): ChrusNode<T> =
        decoder.decodeStructure(descriptor) {
            var type: Int = -2
            var id: Long = -1
            var name: String = ""
            lateinit var nodeData: T
            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> type = decodeIntElement(descriptor, 0)
                    1 -> id = decodeLongElement(descriptor, 1)
                    2 -> name = decodeStringElement(descriptor, 2)
                    3 -> nodeData = decodeSerializableElement(descriptor, 3, dataSerializer)
                    CompositeDecoder.DECODE_DONE -> break
                    else -> error("Unexpected index: $index")
                }
            }
            require(type > -2 && id > -1)
            ChrusNode(type, id, name, nodeData)
        }
}

@Serializable
sealed class ChrusData : ChrusSerializable

@Serializable
data class ChrusSound(var source: String, var playmode: Int, var gain: Float,
                 var pan: Float, var speed: Float) : ChrusData() {
    override fun serialize(): String {
        return "I'm loud and annoying!"
    }
}

@Serializable
data class ChrusAudiostream(var source: String, var playmode: Int, var gain: Float,
                              var pan: Float, var speed: Float) : ChrusData() {
    override fun serialize(): String {
        return "I stream everywhere!"
    }
}

@Serializable
class ChrusScene(var currentCamera: Long) : ChrusData() {
    override fun serialize(): String {
        return "I love making a scene!"
    }
}

@Serializable
class ChrusScript(var source: String) : ChrusData() {
    override fun serialize(): String {
        return "Fuck you!"
    }
}

@Serializable
class ChrusCamera(var sx: Float, var sy: Float, var sw: Float, var sh: Float,
                  var vx: Float, var vy: Float, var vw: Float, var vh: Float) : ChrusData() {
    override fun serialize(): String {
        return "I'm a camera!"
    }
}

@Serializable
data class ChrusSprite(var x: Float, var y: Float, var flipping: Int, var sx: Float,
                  var sy: Float, var rotation: Float, var layer: Int, var visible: Boolean,
                  var source: String) : ChrusData() {
    override fun serialize(): String {
        return "Who am I?"
    }
}

@Serializable
class ChrusPrimitiveHighLevel(var layer: Int = 0, var visible: Boolean = true, var shape: Int = 0,
                              var x1: Float = 0f, var y1: Float = 0f, var x2: Float = 0f, var y2: Float = 0f,
                              var x3: Float = 0f, var y3: Float = 0f, var rx: Float = 0f, var ry: Float = 0f,
                              var thickness: Float = 0f, var color: RGBA = RGBA()) : ChrusData() {
    override fun serialize(): String {
        return "I'm a high-level primitive."
    }

}