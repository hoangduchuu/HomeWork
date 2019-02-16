package net.hdhuu.cache.post

import io.reactivex.Observable
import net.hdhuu.cache.post.mapper.PostEntityMapper
import net.hdhuu.cache.post.models.CachedPost
import net.hdhuu.datasource.mapper.PostMapper
import net.hdhuu.datasource.model.PostDataEntity
import net.hdhuu.datasource.post.repository.PostCache
import java.util.*

class PostCacheImpl(val mapper: PostEntityMapper): PostCache {

    var lists: ArrayList<CachedPost> = ArrayList()
    val data: ArrayList<PostDataEntity> = ArrayList()
    override fun getAllPosts(): Observable<List<PostDataEntity>> {
        lists.add(CachedPost(2L, "a cache", 3.0))
        lists.add(CachedPost(4L, "b cache", 3.0))
        for (list in lists) {
            data.add(mapper.mapFromCached(list))
        }
        return Observable.fromArray(data)
    }


}