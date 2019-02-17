package net.hdhuu.cache.post

import android.util.Log
import io.reactivex.Completable
import io.reactivex.Observable
import net.hdhuu.cache.post.mapper.PostEntityMapper
import net.hdhuu.cache.room.TweetDatabase
import net.hdhuu.datasource.model.PostDataEntity
import net.hdhuu.datasource.post.repository.PostCache
import java.util.*

class PostCacheImpl(val mapper: PostEntityMapper, val tweetDatabase: TweetDatabase) : PostCache {

    override fun getAllPosts(): Observable<List<PostDataEntity>> {
        val data: ArrayList<PostDataEntity> = ArrayList()
        tweetDatabase.postDAO().getAllPosts().forEach { it ->
            data.add(mapper.mapFromCached(it))
        }
        return Observable.fromArray(data)
    }

    override fun insertPost(post: PostDataEntity): Completable {
        return Completable.defer {
            tweetDatabase.postDAO().insertPost(mapper.mapToCached(post))
            Completable.complete()
        }
    }

    override fun deletePost(postID: String): Completable {
        return Completable.defer {
            tweetDatabase.postDAO().deletePostByID(postID)
            Completable.complete()
        }
    }

    override fun deleteAllPosts(): Completable {
        return Completable.defer {
            tweetDatabase.postDAO().clearAllPosts()
            Completable.complete()
        }
    }

    override fun updatePost(post: PostDataEntity): Completable {
        return Completable.defer {
            tweetDatabase.postDAO().updatePost(mapper.mapToCached(post))
            Completable.complete()
        }
    }
}