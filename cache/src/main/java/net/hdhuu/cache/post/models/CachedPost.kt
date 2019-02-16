package net.hdhuu.cache.post.models


/**
 * Model used solely for the caching of a bufferroo
 */
//@Entity(tableName = BufferooConstants.TABLE_NAME)
data class CachedPost(

//        @PrimaryKey
        var id: Long,
        val content: String,
        val createAt: Double
)