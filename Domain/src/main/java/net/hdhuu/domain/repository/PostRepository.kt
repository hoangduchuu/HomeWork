package net.hdhuu.domain.repository

import io.reactivex.Observable
import net.hdhuu.domain.model.Post

interface PostRepository {
    fun getAllPosts() : Observable<List<Post>>
    fun postMessage(message:String):Observable<Boolean>
    fun deleteMessage(postID: Int): Observable<Boolean>
    fun updateMessage(postID: Int, message:String):Observable<Boolean>
    fun postMultipalMessages(messages: List<String>): Observable<Boolean>
}