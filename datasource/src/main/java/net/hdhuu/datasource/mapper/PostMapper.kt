package net.hdhuu.datasource.mapper

import net.hdhuu.datasource.model.PostDataEntity
import net.hdhuu.domain.model.Post


class PostMapper : BaseMapper<Post, PostDataEntity>() {
    override fun mapToEntity(o2: PostDataEntity): Post {
        return Post(o2.id, o2.content, o2.createAt)
    }

    override fun mapFromEntity(o1: Post): PostDataEntity {
        return PostDataEntity(o1.id,o1.content,o1.createAt)
    }

}