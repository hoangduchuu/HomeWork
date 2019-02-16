package net.hdhuu.cache.post.mapper

import net.hdhuu.cache.post.models.CachedPost
import net.hdhuu.datasource.model.PostDataEntity

/**
 * Map a [CachedBufferoo] instance to and from a [Bufferoo] instance when data is moving between
 * this later and the Data layer
 */
open class PostEntityMapper :
        EntityMapper<CachedPost, PostDataEntity> {

    /**
     * Map a [Bufferoo] instance to a [CachedBufferoo] instance
     */
    override fun mapToCached(type: PostDataEntity): CachedPost {
        return CachedPost(type.id, type.content, type.createAt)
    }

    /**
     * Map a [CachedBufferoo] instance to a [Bufferoo] instance
     */
    override fun mapFromCached(type: CachedPost): PostDataEntity {
        return PostDataEntity(type.id, type.content, type.createAt)
    }

}