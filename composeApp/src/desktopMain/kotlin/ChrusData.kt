interface ChrusData {
    fun serialize(): String
}

class ChrusScript(private var source: String) : ChrusData {
    override fun serialize(): String {
        return "Fuck you!"
    }
}