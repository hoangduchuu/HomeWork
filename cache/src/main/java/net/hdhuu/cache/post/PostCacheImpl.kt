package net.hdhuu.cache.post

import android.util.Log
import io.reactivex.Observable
import net.hdhuu.cache.post.mapper.PostEntityMapper
import net.hdhuu.cache.room.TweetDatabase
import net.hdhuu.datasource.model.PostDataEntity
import net.hdhuu.datasource.post.repository.PostCache
import java.util.*

class PostCacheImpl(val mapper: PostEntityMapper,val tweetDatabase: TweetDatabase): PostCache {

    val data: ArrayList<PostDataEntity> = ArrayList()
    override fun getAllPosts(): Observable<List<PostDataEntity>> {
        tweetDatabase.postDAO().getAllPosts().forEach {it->
            data.add(mapper.mapFromCached(it))
        }
        return Observable.fromArray(data)
    }

    override fun insertPost(post: PostDataEntity) {
        tweetDatabase.postDAO().insertPost(mapper.mapToCached(post))
    }

    override fun deletePost(postID: String) {
        tweetDatabase.postDAO().deletePostByID(postID)
    }

    override fun deleteAllPosts() {
        tweetDatabase.postDAO().clearAllPosts()
    }

    override  fun updatePost(post: PostDataEntity) {
        tweetDatabase.postDAO().updatePost(mapper.mapToCached(post))
    }
}