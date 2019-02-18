package net.hdhuu.datasource.post

import io.reactivex.Observable
import net.hdhuu.datasource.mapper.PostMapper
import net.hdhuu.datasource.model.PostDataEntity
import net.hdhuu.datasource.post.repository.PostRemote
import net.hdhuu.datasource.post.repository.PostCache
import net.hdhuu.domain.model.Post


/**
 * the logic to to filtering data will be over here
 */
class PostDataStoreFactory(val cache: PostCache, val remote: PostRemote, val postMapper: PostMapper) {
    fun getAllPost(): Observable<List<Post>> {
        return  cache.getAllPosts()
            .concatWith(cache.getAllPosts())
            .map { t: List<PostDataEntity> ->
                postMapper.mapToEntity(t)
            }
    }
}