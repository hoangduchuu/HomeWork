package net.hdhuu.datasource.post

import io.reactivex.Observable
import net.hdhuu.datasource.mapper.PostMapper
import net.hdhuu.datasource.model.PostDataEntity
import net.hdhuu.datasource.post.repository.PostCache
import net.hdhuu.domain.model.Post
import net.hdhuu.domain.repository.PostRepository
import java.util.*

class PostRepository(val factory: PostDataStoreFactory, val postCache: PostCache, val mapper: PostMapper) :
    PostRepository {
    override fun getAllPosts(): Observable<List<Post>> {
        return factory.getAllPost();
    }

    //FIXME for online version, use remote module to post and cache
    override fun postMessage(message: String): Observable<Boolean> {
        return postCache.insertPost(PostDataEntity(content = message, createAt = System.currentTimeMillis().toDouble()))
            .toObservable()


    }

    override fun deleteMessage(postID: Int): Observable<Boolean> {
        return Observable.create { emitter ->
            postCache.deletePost(postID.toString())
            emitter.onNext(true)
        }
    }

    override fun updateMessage(postID: Int, message: String): Observable<Boolean> {
        return Observable.create { emitter ->
            postCache.updatePost(mapper.mapFromEntity(Post(id = postID, content = message)))
            emitter.onNext(true)
        }
    }

    override fun postMultipalMessages(messages: List<String>): Observable<Boolean> {
            val dataMapped: MutableList<PostDataEntity> = ArrayList()
            for (i in 0..messages.size - 1) {
                dataMapped.add(mapper.mapFromEntity(Post(content = messages[i])))
            }
          return  postCache.insertMultilePost(dataMapped).toObservable()


    }
}