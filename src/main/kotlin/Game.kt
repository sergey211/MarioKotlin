
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.Audio
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.Image
import org.w3c.dom.events.KeyboardEvent

const val CANVAS_WIDTH = 762.0
const val CANVAS_HEIGHT = 720.0
const val BACKGROUND_COLOR = "#7974FF"

val musicTheme = Audio(src = "aboveground_bgm.ogg")
var dt = 0.0
lateinit var context: CanvasRenderingContext2D
const val CELL_SIZE = 16.0
var hero : Entity = Entity(x = 4.0, y = 4.0, Sprite(HERO_FORWARD_IMAGE, si = 3, sj = 1))


infix fun <A, B> A.x(b: B) = Pair(this, b)

    val level = Level(

        floor = listOf(0 x 68, 71 x 85, 89 x 152, 155 x 212),
        bushes = listOf(11 x 3, 23 x 1, 59 x 3, 89 x 2, 137 x 2, 71 x 1, 106 x 3, 118 x 1, 167 x 1),
        clouds = listOf(
            7 x 8 x 1, 19 x 9 x 1, 27 x 8 x 3, 36 x 9 x 2, 56 x 8 x 1, 67 x 9 x 1, 75 x 8 x 3,
            87 x 9 x 1, 103 x 9 x 1, 123 x 8 x 3, 132 x 9 x 2, 152 x 8 x 1, 163 x 9 x 1,
            171 x 8 x 3, 180 x 9 x 2, 200 x 8 x 1
        ),
        bricks = listOf(
            20 x 3 x 5, 77 x 3 x 3, 80 x 7 x 8, 91 x 7 x 4, 94 x 3 x 1,
            117 x 3 x 1, 100 x 3 x 2, 120 x 7 x 4, 128 x 7 x 4, 129 x 3 x 2, 168 x 3 x 4
        ),
        hills = listOf(0 x 2, 16 x 1, 48 x 2, 64 x 1, 96 x 2, 111 x 1, 144 x 2, 160 x 1, 192 x 2),
        pipes = listOf(28 x 2, 38 x 3, 46 x 4, 163 x 2, 179 x 2),
        pandoras = listOf(
            16 x 3, 21 x 3, 22 x 7, 23 x 3, 78 x 3, 94 x 7, 105 x 3,
            108 x 3, 108 x 7, 111 x 3, 129 x 7, 130 x 7, 170 x 3
        ),
        forwardSteps = listOf(134 x 4 x 0, 148 x 4 x 1, 181 x 8 x 1),
        backwardSteps = listOf(140 x 4 x 1, 155 x 4 x 0),


//        floorSpriteB = Sprite.tile(0,0),
//        bushSpritesB = Sprite.bush(11,9),
//        cloudSprites = Sprite.cloud(0,20),
//
//        brickSpriteB = Sprite.tile(1,0),
//        wallSpriteB = Sprite.tile(0,1),
//        heroSpriteB = Sprite(HERO_FORWARD_IMAGE, si = 5, sj = 2)
        )


object Images {
    operator fun get(src: String) = images[src]!!
    private val images = mutableMapOf<String, Image?>()
    private fun isReady() = images.values.all { it != null }
    fun load(vararg imagesSrc: String, onload: () -> Unit) {
        imagesSrc.forEach { src ->
            val image = Image()
            images[src] = null
            image.src = src
            image.onload = {
                images[src] = image
                if (isReady()) {
                    onload()
                }
            }
        }
    }
}


fun drawSprite(sprite: Sprite, x: Double, y: Double) {
    context.drawImage(
        Images[sprite.src],
        sx = sprite.si * CELL_SIZE + 2/3.0, // +1
        sy = sprite.sj * CELL_SIZE + 2/3.0, // +1
        sw = sprite.w * CELL_SIZE - 4/3.0, // -2
        sh = sprite.h * CELL_SIZE - 3/3.0, // -2
        dw = sprite.w * CELL_SIZE,
        dh = sprite.h * CELL_SIZE,
        dx = x * CELL_SIZE,
        dy = y * CELL_SIZE,
    )
}



    fun main() {
        class Hero : Entity(x = 4.0, y = 12.0, Sprite(HERO_FORWARD_IMAGE, si = 5, sj = 2)) {
    fun moveRight(){
        sprite.src = HERO_FORWARD_IMAGE
        x += 0.05
    }

    fun moveLeft(){
        sprite.src = HERO_BACKWARD_IMAGE
        x -= 0.05
    }
        }
        var hero = Hero()

        window.onload = {

            document.addEventListener("keydown", { event ->
                val keyboardEvent = event as KeyboardEvent
                when (keyboardEvent.code) {
                    "ArrowLeft" ->  hero.x -= 0.3//level.windowX -= 1.3 //drawCloud(11, 3)//
                    "ArrowRight" ->  hero.x += 0.3 //level.windowX += dt * 10 //level.windowX += 1.3 //drawCloud(7, 2) //

                    "ArrowUp" -> musicTheme.play()    // start
                    "ArrowDown" -> musicTheme.pause()  // stop
//                    "Space" ->  window.alert(level.entities.size.toString())
                    "Space" ->  window.alert(hero.y.toString())
                }
                //render()
            })


            val canvas: HTMLCanvasElement = document.getElementById("canvas") as HTMLCanvasElement
            context = canvas.getContext("2d") as CanvasRenderingContext2D
            context.scale(3.0, 3.0)
            context.fillStyle = "#7974FF"
            context.fillRect(0.0, 0.0, 762.0, 720.0)

            Images.load(
                TILES_IMAGE,
                HERO_FORWARD_IMAGE,
                HERO_BACKWARD_IMAGE
            )
            {
//                window.requestAnimationFrame(::update)

                level.floor.forEach { (i, size) ->
                    level.addFloor(i, size)
                }

                level.bushes.forEach { (i, size) ->
                    level.addBush(i, size)
                }

                level.clouds.forEach { (indices, size) ->
                    val (i, j) = indices
                    level.addCloud(i, 11-j, size)
                }

                level.pipes.forEach { (i, j) ->
                    level.addPipe(i, 13-j)
                }

                level.hills.forEach { (i, size) ->
                    level.addHill(i, size)
                }

                level.bricks.forEach { (indices, size)->
                    val (i, j) = indices
                    level.addBricks(i, j, size)
                }

                level.pandoras.forEach { (i, size)->
                    level.addPandoras(i, size)
                }

                level.forwardSteps.forEach { (indices, size) ->
                    val (i, j) = indices
                    level.addForwardSteps(i, j, size)
                }

                level.backwardSteps.forEach { (indices, size) ->
                    val (i, j) = indices
                    level.addBackwardSteps(i, j, size)
                }

                //level.addHeroR()
//                level.entities += Entity(5, 5, floorSprite)
//                level.entities += Entity(x = 7.0, y = 8.0, Sprite(HERO_FORWARD_IMAGE, si = 5, sj = 2))
                level.entities += hero
                window.requestAnimationFrame(::update)
                render0()

            }

            Unit

        }
  }


    fun render0() {
        context.clearRect(0.0, 0.0, CANVAS_WIDTH, CANVAS_HEIGHT)
        context.fillStyle = BACKGROUND_COLOR
        context.fillRect(0.0, 0.0, CANVAS_WIDTH, CANVAS_HEIGHT)
        level.render()
    }


fun update(timestamp: Double){
    level.update(timestamp)
    render0()
    window.requestAnimationFrame(::update)
}
