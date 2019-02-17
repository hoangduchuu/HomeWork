package net.hdhuu.cache.post.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Post")
data class CachedPost(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var content: String = "",
    var createAt: Double = 0.0
) {
    constructor(content: String, createAt: Double) : this() {
        this.content = content
        this.createAt = createAt
    }
}