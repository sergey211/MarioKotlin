typealias IntTriple = Pair<Pair<Int, Int>, Int>
typealias IntPair = Pair<Int, Int>
var gameTime: Double = Double.NaN


class Level(
    val floor: List<IntPair> = emptyList(),
    val bushes: List<IntPair> = emptyList(),
    val hills: List<IntPair> = emptyList(),
    val clouds: List<IntTriple> = emptyList(),
    val pipes: List<IntPair> = emptyList(),
    val bricks: List<IntTriple> = emptyList(),
    val pandoras: List<IntPair> = emptyList(),
    val forwardSteps: List<IntTriple> = emptyList(),
    val backwardSteps: List<IntTriple> = emptyList(),

//    val cloudSprites: List<Sprite> = emptyList(),
//    val bushSpritesB: List<Sprite> = emptyList(),
//    val hillSprites: List<Sprite> = emptyList(),
//    val pipeSprites: List<Sprite> = emptyList(),
//    val floorSpriteB: Sprite,
//    val brickSpriteB: Sprite,
//    val wallSpriteB: Sprite,
//    val heroSpriteB: Sprite
)

{
    var windowX = 0.0
    var entities = setOf<Entity>()
    fun update(timestamp: Double) {
//        dt = (if (gameTime.isNaN()) 0.0
//        else timestamp - gameTime) / 1000
        if (gameTime.isNaN())
        {dt = 0.0}
        else
        {dt = (timestamp - gameTime) / 1000 }
        gameTime = timestamp

        KeyboardInput.initKeyboardListeners()
             if (KeyboardInput.isRightPressed())
             {
                 level.windowX += dt * 10
                 hero.x +=  0.3
             }
          else if (KeyboardInput.isLeftPressed())
             {
                 level.windowX -= dt * 10
                 hero.x -=  0.3
             }
    }



    fun render() {
    for (entity in entities) {
        if (entity.x + entity.sprite.w > windowX && entity.x < windowX + 16) {
            drawSprite(entity.sprite, entity.x - windowX, entity.y)
        }
    }
}

fun addFloor(start: Int, end: Int) {
    for (j in 15 downTo 13) {
        for (i in start..end) {
            level.entities += Entity(i, j, floorSprite)
        }
    }
}

    fun addHeroR()
    {
       //   level.entities += hero
    }

//    fun deleteHero(i: Double, j: Double)
//    { //level.entities.rem  { it == target }
//    }
    fun addHeroL(i: Double, j: Double)
    {
      //  level.entities = level.entities.minusElement(Entity(hero.x, j, heroSpriteL))
      //  level.entities = level.entities.minusElement(Entity(hero.x, j, heroSpriteR))
         // hero.x -= 0.5
        level.entities = level.entities.plusElement(Entity(i, j, heroSpriteL))
    }

    fun addPipe(i: Int, j: Int) {
        level.entities += Entity(i, j, pipeSprite)
        if (j < 11) {
            for (k in 10 downTo  j){
                level.entities += Entity(i, k+2, pipeSpriteBott)}
        }
    }



    fun addHillSection(i:Int, height:Int, size:Int) {
        for(k in 0 until height) { //
            for (j in 0 until height) {
                level.entities += Entity(i+j, 12-j, hillSprites[0])
                for (n in 1..size-2*j) {
                    level.entities += Entity(i + j + n, 12-j, hillSprites[4])
                }
                level.entities += Entity(size+i-j+1, 12-j, hillSprites[2])
            }
        }
    }


    fun addHill(i: Int, height: Int) {
        for(j in 0 until height) {
            val size = height*2-1 // calculate section size
            addHillSection(i, height, size)
        }
        level.entities += Entity(i+height, 12-height, hillSprites[1])
    }


    //  https://stepik.org/lesson/825778/step/4?unit=829291


    fun addBush(i: Int, length: Int) {

        level.entities += Entity(i, 12, bushSprites[0])  // left side
        for (n in 1 .. length) {
            level.entities += Entity(i+n, 12, bushSprites[1]) // middle
        }
        level.entities += Entity(i+length+1, 12, bushSprites[2])  // right side
    }


    fun addCloud(i: Int, j: Int, length: Int) {
        level.entities += Entity(i, j, cloudSpriteB[0]) // left side
        for (n in 1 .. length) {
            level.entities += Entity(i+n, j, cloudSpriteB[1]) // middle
        }
        level.entities += Entity(i+length+1, j, cloudSpriteB[2])  // right side
    }


    fun addBricks(start: Int, height: Int, length: Int) {
        for (i in start..start+length-1) {
            level.entities += Entity(i, 12-height, brickSprite)
        }
    }

    fun addForwardSteps(i: Int, height: Int, length: Int) {
        for (k in 0 .. height-1) {
            for (l in 0..k)
            {level.entities += Entity(i+k , 12-k+l, wallSprite)}
            level.entities += Entity(i+k , 12-k, wallSprite)
        }
        for (k in 0 until length)
        {
            for (j in 0 until height) {
                level.entities += Entity(i+height+k , 12-j, wallSprite)
            }
            level.entities += Entity(i+height+k , 12-height+1, wallSprite)
        }


    }






    fun addBackwardSteps(i: Int, height: Int, length: Int) {
        for (k in 0 until length)
        {
            for (j in 0 until height) {
                level.entities += Entity(i+k , 12-j, wallSprite)
            }
            level.entities += Entity(i+k , 12-height+1, wallSprite)
        }


        for (k in height-1 downTo  0) {
            for (l in k downTo  0) {
                level.entities += Entity(i+length+height-1-k, 8+height-l, wallSprite)
            }
            level.entities += Entity(i +length + k , 9 + k, wallSprite)
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


