    open class Entity(var x: Double, var y: Double, val sprite: Sprite) {
    constructor(i: Int, j: Int, sprite: Sprite) : this(i.toDouble(), j.toDouble(), sprite)

}