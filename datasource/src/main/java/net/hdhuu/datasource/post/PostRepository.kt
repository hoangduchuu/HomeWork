package net.hdhuu.datasource.post

import io.reactivex.Observable
import net.hdhuu.domain.model.Post
import net.hdhuu.domain.repository.PostRepository

class PostRepository(val factory: PostDataStoreFactory) : PostRepository {
    override fun getAllPosts(): Observable<List<Post>> {
        return factory.getAllPost();
    }
}