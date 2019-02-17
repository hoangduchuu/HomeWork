package net.hdhuu.remote.post

import io.reactivex.Observable
import net.hdhuu.datasource.model.PostDataEntity
import net.hdhuu.datasource.post.repository.PostRemote
import net.hdhuu.remote.mapper.PostEntityMapper
import net.hdhuu.remote.model.PostRemoteEntity
import java.util.*

class PostRemoteImpl(val mapper: PostEntityMapper) : PostRemote {
    var lists: ArrayList<PostRemoteEntity> = ArrayList()
    val data: ArrayList<PostDataEntity> = ArrayList()

    override fun getAllPosts(): Observable<List<PostDataEntity>> {
        lists.add(PostRemoteEntity(2, "a remote", 3.0))
        lists.add(PostRemoteEntity(4, "b remote", 3.0))

        for (list in lists) {
            data.add(mapper.mapFromRemote(list))
        }
        return Observable.fromArray(data)
    }

}