package net.hdhuu.splee

import java.util.*

/**
 * give message-String and receive messages smaller than 50 characters in array
 */

/**
 *
 * - calculate MINIMUM line include message and prefix
 * - with MINIMUM line, we append the prefix with content match 50 characters conditional by word
 * - when message is match with MINIMUM - line we check our words is empty or not,
 *      + if empty, ---> we are lucky and splitMessages
 *      + else,--> we calculate how many line will be add to minimum line and render all message again :(
 *       (recursive will be call  😓)
 *
 *
 *
 */


const val MESSAGE_PER_LINE = 50
var startAtIndex = 0;
var stopAtIndex = 0;

const val SPACE = " ";

class SplitMessage {
    fun splitMessages(message: String): List<String> {
        if (message.length <= 50) {
            return arrayListOf(message)
        }
        return renderMessages(getWorldsArray(message), calculateMessageLine(message))
    }

    private fun calculateMessageLine(longMessages: String): Int {
        val messageLineCount = longMessages.length / MESSAGE_PER_LINE + appendOneLineIfNeeded(longMessages)
        val prefixLineCount = getPrefixMessageCount(0, messageLineCount) / MESSAGE_PER_LINE
        val totalLineCounts = messageLineCount + prefixLineCount;
        return totalLineCounts
    }

    /**
     * Recursive function to split the message with calculated line
     *
     * @Words : arrayOf words in input messsage
     * @lineCount : calculated predict line count
     * @return : message line small than 50 character
     */
    private fun renderMessages(words: LinkedList<String>, lineCount: Int): List<String> {
        val results = ArrayList<String>()
        stopAtIndex = 0
        for (i in 1..lineCount) {
            val prefixText = getPrefix(i, lineCount)
            val msg = getMessageRow(prefixText, words)
            results.add(msg)
        }


        // when message is redundant when we split
        // we calculate again and number of line is plus with redundant message line calculated and re-render message-array again
        if (stopAtIndex < words.size - 1) {
            val redundantMessages = cutRedundantMessage(words).trim()
            val lines = lineCount + calculateMessageLine(redundantMessages)
            return renderMessages(words, lines)
        }
        return results
    }

    private fun getPrefix(i: Int, predictLineNumber: Int) =
        i.toString() + "/" + predictLineNumber + SPACE

    private fun getPrefixMessageCount(from: Int, to: Int): Int {
        //FIXME programmatically ?
        val builder = StringBuilder()
        for (i in from..to) {
            builder.append(i).append("/").append(to).append(" ")
        }
        return builder.toString().length
    }

    private fun cutRedundantMessage(words: LinkedList<String>): String {
        val stringBuilder = StringBuilder()
        for (i in stopAtIndex until words.size) {
            stringBuilder.append(words[i]).append(SPACE)
        }
        return stringBuilder.toString()
    }


    /**
     * Concat message with word ( match conditional < 50 characters )
     *
     * @param prefix : prefix like: 1/10
     * @param words : message words in arrays
     */
    private fun getMessageRow(prefix: String, words: LinkedList<String>): String {
        var message = prefix
        for (i in stopAtIndex until words.size) {
            message = message + words[i] + SPACE
            startAtIndex = i
            stopAtIndex = i

            // When message length more than 50 character--> remove last word with space
            if (message.length - 1 > MESSAGE_PER_LINE) {
                message = message.substring(0, message.lastIndexOf(SPACE))
                message = message.substring(0, message.lastIndexOf(SPACE))
                break
            }
        }
        return message
    }

    private fun appendOneLineIfNeeded(msg: String): Int {
        if (msg.length % MESSAGE_PER_LINE == 0) return 0
        return 1
    }

    private fun getWorldsArray(s: String): LinkedList<String> {
        val result = LinkedList<String>()
        val arr = s.split(" ")

        arr.forEach { ss ->
            result.add(ss)
        }
        return result
    }
}


