package net.hdhuu.datasource.post.repository

import io.reactivex.Completable
import io.reactivex.Observable
import net.hdhuu.datasource.model.PostDataEntity

interface PostCache {
    fun getAllPosts(): Observable<List<PostDataEntity>>
    fun insertPost(post: PostDataEntity): Completable
    fun insertMultilePost(posts: List<PostDataEntity>):Completable
    fun deletePost(postID: String) :Completable
    fun deleteAllPosts():Completable
    fun updatePost(post: PostDataEntity):Completable
}