package net.hdhuu.datasource.post.repository

import io.reactivex.Observable
import net.hdhuu.datasource.model.PostDataEntity

interface PostCache {
    fun getAllPosts(): Observable<List<PostDataEntity>>
    fun insertPost(post: PostDataEntity)
    fun deletePost(postID: String)
    fun deleteAllPosts()
    fun updatePost(post: PostDataEntity)
}