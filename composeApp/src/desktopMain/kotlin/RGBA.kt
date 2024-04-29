import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object RgbaAsHexStringSerializer : KSerializer<RGBA> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("RGBA", PrimitiveKind.STRING)
    override fun deserialize(decoder: Decoder): RGBA {
        val string = decoder.decodeString()

        if (string[0] != '#') throw IllegalArgumentException("Not a valid color!")
        val r: UByte = ((string[1].digitToInt(16) shl 8) + (string[2].digitToInt(16))).toUByte()
        val g: UByte = ((string[3].digitToInt(16) shl 8) + (string[4].digitToInt(16))).toUByte()
        val b: UByte = ((string[5].digitToInt(16) shl 8) + (string[6].digitToInt(16))).toUByte()
        val a: UByte = ((string[7].digitToInt(16) shl 8) + (string[8].digitToInt(16))).toUByte()
        return RGBA(r,g,b,a)
    }
    override fun serialize(encoder: Encoder, value: RGBA) {
        fun byteToHex(x: UByte): String {
            return x.toString(16).padStart(2, '0')
        }

        val string = "#" + byteToHex(value.r) + byteToHex(value.g) + byteToHex(value.b) + byteToHex(value.a)
        encoder.encodeString(string)
    }
}

@Serializable
data class RGBA(var r: UByte = 255u, var g: UByte = 255u, var b: UByte = 255u, var a: UByte = 255u)