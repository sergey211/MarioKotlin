import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.events.KeyboardEvent

object KeyboardInput {

    var pressedKeys: Set<String> = emptySet()

    fun initKeyboardListeners(){
        document.addEventListener("keydown", { event ->
            val keyboardEvent = event as KeyboardEvent
            KeyboardInput.pressedKeys += keyboardEvent.code
        })

        document.addEventListener("keyup", { event ->
            val keyboardEvent = event as KeyboardEvent
            KeyboardInput.pressedKeys -= keyboardEvent.code
        })

        window.addEventListener("blur", {
            KeyboardInput.pressedKeys = emptySet()
        })
    }

    fun isLeftPressed() =  "ArrowLeft" in pressedKeys
    fun isRightPressed() = "ArrowRight" in pressedKeys
    fun isUpPressed() = "ArrowUp" in pressedKeys
    fun isDownPressed() = "ArrowDown" in pressedKeys
    fun isJumpPressed() = "Space" in pressedKeys
//    fun isRunPressed() = "ArrowRight" in pressedKeys
//    fun isRunBackPressed() = "ArrowLeft" in pressedKeys

}