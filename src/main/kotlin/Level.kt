typealias IntTriple = Pair<Pair<Int, Int>, Int>
typealias IntPair = Pair<Int, Int>
//private var windowX = 0.0
var gameTime: Double = Double.NaN

class Level(
    // val floor: List<IntRange> = emptyList(),
    val floor: List<IntPair> = emptyList(),
    val bushes: List<IntPair> = emptyList(),
    val hills: List<IntPair> = emptyList(),
    val clouds: List<IntTriple> = emptyList(),
    val pipes: List<IntPair> = emptyList(),
    val bricks: List<IntTriple> = emptyList(),
    val pandoras: List<IntPair> = emptyList(),
    val forwardSteps: List<IntTriple> = emptyList(),
    val backwardSteps: List<IntTriple> = emptyList(),

    val cloudSprites: List<Sprite> = emptyList(),
    val bushSprites: List<Sprite> = emptyList(),
    val hillSprites: List<Sprite> = emptyList(),
    val pipeSprites: List<Sprite> = emptyList(),
    val floorSprite: Sprite,
    val brickSprite: Sprite,
    val wallSprite: Sprite,
)

{

//    init {
//        level1.bushes.forEach { (i, size) ->
//            level.addBush(i, size)
//        }
//    }

    var windowX = 2.0
    var entities = setOf<Entity>()



    // val max = if (a > b) a else b // в одну строку
    var dir = true
    fun update(timestamp: Double) {
        dt = (if (gameTime.isNaN()) 0.0
        else timestamp - gameTime) / 1000
        gameTime = timestamp

        if (level.windowX >= 190) {
            dir = false}
        else if (level.windowX <= 0){
            dir = true
            dt = 0.0
        }

        // if (dir && KeyboardInput.isRunPressed()){
       // if (KeyboardInput.isRunPressed()){
//             if (dir){
        KeyboardInput.initKeyboardListeners()
             if (KeyboardInput.isRightPressed()){
            level.windowX += dt * 10}
          else  if (KeyboardInput.isLeftPressed())
          { level.windowX -= dt * 10}
      //  else if (KeyboardInput.isRunBackPressed())
  //      { level.windowX -= dt * 10}
//        else if (KeyboardInput.isJumpPressed())
//        { musicTheme.play()  }

    }



    fun render() {

    for (entity in entities) {
       // drawSprite(entity.sprite, entity.x, entity.y)
        if (entity.x + entity.sprite.w > windowX && entity.x < windowX + 16) {
            drawSprite(entity.sprite, entity.x - windowX, entity.y)
        }
    }
}

fun addFloor(start: Int, end: Int) {
    for (j in 15 downTo 13) {
        for (i in start..end) {
            entities += Entity(i, j, floorSprite)
        }
    }
}


    //                addPipe(i, 13-j) // 2,3,4  = 11,  10,9
    fun addPipe(i: Int, j: Int) {
        level.entities += Entity(i, j, pipeSprite)
        if (j < 11) {
            for (k in 10 downTo  j){
                level.entities += Entity(i, k+2, pipeSpriteBott)}
        }
    }



    //  https://stepik.org/lesson/825778/step/4?unit=829291


//    fun addBricks(start: Int, end: Int, size: Int) {
//        //    for (j in 9 downTo 7) {
//        for (i in end..size) {
//            entities += Entity(i, 9, brickSprite)
//        }
//        //    }
//    }
//    20 x 3 x 5, 77 x 3 x 3, 80 x 7 x 8, 91 x 7 x 4, 94 x 3 x 1,
//    117 x 3 x 1, 100 x 3 x 2, 120 x 7 x 4, 128 x 7 x 4, 129 x 3 x 2, 168 x 3 x 4


    /*
    fun addBricks(i: Int, j: Int, length: Int) {
                for (it in i until length) {
                    if(!level1.pandoras.contains(it x j)) {
                        entities += Entity(it , j, brickSprite)
                    }
                }
            }
*/

    fun addBush(i: Int, length: Int) {

        level.entities += Entity(i, 12, bushSprites[0])
        //drawSprite(bushSprites[0], i , j = 12) // left side
        for (n in 1 .. length) {
            //drawSprite(bushSprites[1], i+n , j = 12) // middle
            level.entities += Entity(i+n, 12, bushSprites[1])
        }
        //drawSprite(bushSprites[2], i+length+1 , j = 12) // right side
        level.entities += Entity(i+length+1, 12, bushSprites[2])
    }


    fun addCloud(i: Int, j: Int, length: Int) {
        level.entities += Entity(i, j, cloudSpriteB[0])
        // drawSprite(cloudSpriteB[0], i , j) // left side
        for (n in 1 .. length) {
            //drawSprite(cloudSpriteB[1], i+n , j) // middle
            level.entities += Entity(i+n, j, cloudSpriteB[1])
        }
        //drawSprite(cloudSpriteB[2], i+length+1 , j) // right side
        level.entities += Entity(i+length+1, j, cloudSpriteB[2])
    }


    fun addBricks(start: Int, height: Int, length: Int) {
        for (i in start..start+length-1) {
            level.entities += Entity(i, 12-height, brickSprite)
        }
    }

    fun addForwardSteps(i: Int, height: Int, length: Int) {
        for (k in 0 .. height-1) {
            for (l in 0..k)
            {level.entities += Entity(i+k , 12-k+l, level.wallSprite)}
            level.entities += Entity(i+k , 12-k, level.wallSprite)
        }
        for (k in 0 until length)
        {
            for (j in 0 until height) {
                level.entities += Entity(i+height+k , 12-j, level.wallSprite)
            }
            level.entities += Entity(i+height+k , 12-height+1, level.wallSprite)
        }


    }






    fun addBackwardSteps(i: Int, height: Int, length: Int) {
        for (k in 0 until length)
        {
            for (j in 0 until height) {
                level.entities += Entity(i+k , 12-j, level.wallSprite)
            }
            level.entities += Entity(i+k , 12-height+1, level.wallSprite)
        }


        for (k in height-1 downTo  0) {
            for (l in k downTo  0) {
                level.entities += Entity(i+length+height-1-k, 8+height-l, level.wallSprite)
            }
            level.entities += Entity(i +length + k , 9 + k, level.wallSprite)
        }

    }

    fun addPandoras(start: Int, height: Int) {
        for (i in start..start) {
            level.entities += Entity(i, 12-height, pandorasSprite)
        }
    }

/*    fun addCloudFun(i: Int, j: Int, length: Int) {
        level.entities += Entity(i+length+1, j, cloudSpriteFun)

    }
*/



}


