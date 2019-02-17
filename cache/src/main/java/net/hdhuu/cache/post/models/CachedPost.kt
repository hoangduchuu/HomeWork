package net.hdhuu.cache.post.models

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Model used solely for the caching of a bufferroo
 */
@Entity(tableName = "Post")
data class CachedPost(
        @PrimaryKey
        var id: Long,
        val content: String,
        val createAt: Double
)