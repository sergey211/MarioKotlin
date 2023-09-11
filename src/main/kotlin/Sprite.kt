const val TILES_IMAGE = "sprites/tiles.png"
const val HERO_FORWARD_IMAGE = "sprites/player.png"
const val HERO_BACKWARD_IMAGE = "sprites/playerl.png"

//data class Sprite(var src: String, val si: Int, val sj: Int, val w: Int = 1, val h: Int = 1)
//val cloudSprite = Sprite(TILES_IMAGE, si = 0, sj = 20, w = 3, h = 2)
//val floorSprite = Sprite(TILES_IMAGE, si = 0, sj = 0, w = 1, h = 1)
//val bushSprites = listOf(
//    Sprite(TILES_IMAGE, si = 11, sj = 9),
//    Sprite(TILES_IMAGE, si = 12, sj = 9),
//    Sprite(TILES_IMAGE, si = 13, sj = 9),
//)
//val cloudSpriteB = listOf(
//    Sprite(TILES_IMAGE, si = 0, sj = 20, h = 2),
//    Sprite(TILES_IMAGE, si = 1, sj = 20, h = 2),
//    Sprite(TILES_IMAGE, si = 2, sj = 20, h = 2),
//)




data class Sprite(var src: String, val si: Int, val sj: Int, val w: Int = 1, val h: Int = 1) {
    companion object {

        fun tile(si: Int, sj: Int, w: Int = 1, h: Int = 1) = Sprite(TILES_IMAGE, si, sj, w, h)

        fun bush(si: Int, sj: Int) = List(3) {
            tile(si + it, sj)
        }

        fun cloud(si: Int, sj: Int) = List(3) {
            tile(si + it, sj)
        }

        fun hill(si: Int, sj: Int) = List(3) {
            tile(si + it, sj)
        }

        fun brick(si: Int, sj: Int) = List(3) {
            tile(si + it, sj)
        }

        fun wall(si: Int, sj: Int) = List(3) {
            tile(si + it, sj)
        }

    }
}


// x начала спрайта, у начала, длина, высота
val cloudSpriteFun = Sprite(TILES_IMAGE, si = 5, sj = 20, w = 3, h = 2)
val floorSprite = Sprite(TILES_IMAGE, si = 0, sj = 0, w = 1, h = 1)
val wallSprite = Sprite(TILES_IMAGE, si = 0, sj = 1, w = 1, h = 1)
val brickSprite = Sprite(TILES_IMAGE, si = 1, sj = 0, w = 1, h = 1)
val pandorasSprite = Sprite(TILES_IMAGE, si = 24, sj = 0, w = 1, h = 1)
val bushSprites = listOf(
    Sprite(TILES_IMAGE, si = 11, sj = 9),
    Sprite(TILES_IMAGE, si = 12, sj = 9),
    Sprite(TILES_IMAGE, si = 13, sj = 9),
)
val cloudSpriteB = listOf(
    Sprite(TILES_IMAGE, si = 0, sj = 20, h = 2),
    Sprite(TILES_IMAGE, si = 1, sj = 20, h = 2),
    Sprite(TILES_IMAGE, si = 2, sj = 20, h = 2),
)

val pipeSprite = Sprite(TILES_IMAGE, si = 0, sj = 8, w = 2, h = 2)
val pipeSpriteBott = Sprite(TILES_IMAGE, si = 0, sj = 9, w = 2, h = 1)
val pipelineSprite = listOf(
    Sprite(TILES_IMAGE, si = 0, sj = 8, w = 2),
    Sprite(TILES_IMAGE, si = 0, sj = 9, w = 2),
    //  Sprite(TILES_IMAGE, si = 2, sj = 20, h = 2),
)

val heroSpriteR = Sprite(HERO_FORWARD_IMAGE, si = 5, sj = 2)
val heroSpriteL = Sprite(HERO_BACKWARD_IMAGE, si = 5, sj = 2)

val hillSprites = listOf(
    Sprite(TILES_IMAGE, si = 8, sj = 10),
    Sprite(TILES_IMAGE, si = 9, sj = 10),
    Sprite(TILES_IMAGE, si = 10, sj = 10),
    Sprite(TILES_IMAGE, si = 8, sj = 11),
    Sprite(TILES_IMAGE, si = 9, sj = 11),
    Sprite(TILES_IMAGE, si = 10, sj = 11),
)
