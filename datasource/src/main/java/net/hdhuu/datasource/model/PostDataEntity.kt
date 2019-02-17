package net.hdhuu.datasource.model

/**
 * Created by Huu Hoang on 2/16/19.
 */
data class PostDataEntity(
    var id: Int = 0,
    var content: String = "",
    var createAt: Double = 0.0
) {
    constructor(content: String, createAt: Double) : this() {
        this.content = content
        this.createAt = createAt
    }
}