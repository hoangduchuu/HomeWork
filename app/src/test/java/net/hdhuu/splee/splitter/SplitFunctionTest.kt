package net.hdhuu.splee.splitter

import net.hdhuu.splee.SplitMessage
import net.hdhuu.splee.splitter.SplittestHelper.Data.inputMessageTest
import net.hdhuu.splee.splitter.SplittestHelper.Data.shortMessage
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import java.util.*
import org.junit.Assert

class SplitFunctionTest {

    private lateinit var splitMessage: SplitMessage
    @Before
    fun setUp() {
        splitMessage = SplitMessage()
    }

    @Test
    fun cantNotAddFirstWordOfBellowMessageToMessageAboveBecauseMessageLengtWillBeOverOf50Characters() {
        val messages = splitMessage.splitMessages(inputMessageTest)
        messages.withIndex().forEach { (index, value) ->
            println(messages[index])
            if (index + 1 == messages.size) {
                return
            }
            val testMessage = "$value ${getFirstWordl(messages[index +1])}"
            Assert.assertTrue(testMessage.length > MESSAGE_PER_LINE)
        }
    }



    @Test
    fun makeSureNoOneMessageOverOf50characters() {
        val messages = splitMessage.splitMessages(message = inputMessageTest)
        messages.withIndex().forEach { (_, value) ->
            Assert.assertTrue(value.length <= MESSAGE_PER_LINE)
        }
    }

    @Test
    fun returnOriginMessageWhenInputMessgeLengtSmallerThan50Characters(){
            Assert.assertEquals(splitMessage.splitMessages(shortMessage)[0] , shortMessage)
    }


    @Test
    fun noSpaceCharacterInlastedMessage(){
        val messages = splitMessage.splitMessages(inputMessageTest)
        messages.withIndex().forEach { (_, value) ->
            val lastedCharacter = value.get(value.lastIndex)
            assertNotEquals(lastedCharacter," ")
        }
    }


    //region helper region
    private fun getFirstWordl(s: String): String {
        return getWorldsArray(s).get(1)
    }
    private fun getWorldsArray(s: String): LinkedList<String> {
        val result = LinkedList<String>()
        val arr = s.split(" ")

        arr.forEach { ss ->
            result.add(ss)
        }
         return result
    }
    //endregion
    companion object {
        private val MESSAGE_PER_LINE = 50
    }
}
