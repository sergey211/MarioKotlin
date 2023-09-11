class Hero1 : Entity(x = 4.0, y = 6.0, Sprite(HERO_FORWARD_IMAGE, si = 5, sj = 2)){
// class Hero : Entity(x = 4.0, y = 12.0, Sprite(HERO_BACKWARD_IMAGE, si = 5, sj = 2)){
    fun moveRight(){
        sprite.src = HERO_FORWARD_IMAGE
        x += 0.05
    }

    fun moveLeft(){
        sprite.src = HERO_BACKWARD_IMAGE
        x -= 0.05
    }
}
