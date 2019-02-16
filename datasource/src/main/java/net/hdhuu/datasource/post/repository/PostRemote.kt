package net.hdhuu.datasource.post.repository

import io.reactivex.Observable
import net.hdhuu.datasource.model.PostDataEntity
import net.hdhuu.domain.model.Post

interface PostRemote {
    fun getAllPosts(): Observable<List<PostDataEntity>>
}