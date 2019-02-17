package net.hdhuu.domain.model

/**
 * Created by Huu Hoang on 2/16/19.
 */
data class Post(
    var id: Int = 0,
    var content: String = "",
    var createAt: Double = 0.0
) {
    constructor(content: String, createAt: Double) : this() {
        this.content = content
    }
}