package net.hdhuu.remote.mapper

import net.hdhuu.datasource.model.PostDataEntity
import net.hdhuu.remote.model.PostRemoteEntity

/*
 * Created by Huu Hoang on 2/16/19.
*/

/**
 * Map a [BufferooModel] to and from a [Bufferoo] instance when data is moving between
 * this later and the Data layer
 */
open class PostEntityMapper : EntityMapper<PostRemoteEntity, PostDataEntity> {

    /**
     * Map an instance of a [BufferooModel] to a [Bufferoo] model
     */
    override fun mapFromRemote(type: PostRemoteEntity): PostDataEntity {
        return PostDataEntity(type.id, type.content, type.createAt)
    }

}