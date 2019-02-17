package net.hdhuu.cache.post

import android.util.Log
import io.reactivex.Observable
import net.hdhuu.cache.post.mapper.PostEntityMapper
import net.hdhuu.cache.post.models.CachedPost
import net.hdhuu.cache.room.TweetDatabase
import net.hdhuu.datasource.model.PostDataEntity
import net.hdhuu.datasource.post.repository.PostCache
import java.util.*

class PostCacheImpl(val mapper: PostEntityMapper,val tweetDatabase: TweetDatabase): PostCache {

    var lists: ArrayList<CachedPost> = ArrayList()
    val data: ArrayList<PostDataEntity> = ArrayList()
    override fun getAllPosts(): Observable<List<PostDataEntity>> {
        lists.add(CachedPost(2, "a cache", 3.0))
        lists.add(CachedPost(4, "b cache", 3.0))
        for (list in lists) {
            data.add(mapper.mapFromCached(list))
        }
        return Observable.fromArray(data)
    }


}