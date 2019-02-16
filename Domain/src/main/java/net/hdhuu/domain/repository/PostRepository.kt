package net.hdhuu.domain.repository

import io.reactivex.Observable
import net.hdhuu.domain.model.Post

interface PostRepository {
    fun getAllPosts() : Observable<List<Post>>
}