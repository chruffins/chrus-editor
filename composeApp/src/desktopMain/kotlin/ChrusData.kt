interface ChrusData {
    fun serialize(): String
}

class ChrusSound(private var source: String, private var playmode: Int, private var gain: Float,
    private var pan: Float, private var speed: Float) : ChrusData {
    override fun serialize(): String {
        return "I'm loud and annoying!"
    }
}

class

class ChrusAudiostream(private var source: String, private var playmode: Int, private var gain: Float,
                 private var pan: Float, private var speed: Float) : ChrusData {
    override fun serialize(): String {
        return "I stream everywhere!"
    }
}

class ChrusScene(private var currentCamera: Long) : ChrusData {
    override fun serialize(): String {
        return "I love making a scene!"
    }
}

class ChrusScript(private var source: String) : ChrusData {
    override fun serialize(): String {
        return "Fuck you!"
    }
}

class ChrusCamera(private var sx: Float, private var sy: Float, private var sw: Float,
                  private var sh: Float, private var vx: Float, private var vy: Float,
                  private var vw: Float, private var vh: Float) : ChrusData {
    override fun serialize(): String {
        return "I'm a camera!"
    }
}

class ChrusSprite(private var x: Float, private var y: Float, private var flipping: Int,
    private var sx: Float, private var sy: Float, private var rotation: Float,
    private var layer: Int, private var visible: Boolean, private var source: String) : ChrusData {

    private var width: Int = 0
    private var height: Int = 0
    override fun serialize(): String {
        return "Who am I?"
    }
}

class ChrusPrimitiveHighLevel(private var layer: Int, private var visible: Boolean,
                              private var shape: Int, private var x1: Float, private var y1: Float,
                              private var x2: Float, private var y2: Float, private var x3: Float,
                              private var y3: Float, private var rx: Float, private var ry: Float,
    private var thickness: Float, private var color: )