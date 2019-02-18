package net.hdhuu.splee.postscreen.model

import net.hdhuu.domain.model.Post
import net.hdhuu.splee.home.model.ResourceState

sealed class PostNewMessageState(val resourceState: ResourceState,
                                 val data: Boolean? = null,
                                 val errorMessage: String? = null) {

    data class Success(private val posts:Boolean): PostNewMessageState(
        ResourceState.SUCCESS,
        posts)

    data class Error(private val message: String? = null): PostNewMessageState(
        ResourceState.SUCCESS,
            errorMessage = message)

    object Loading: PostNewMessageState(ResourceState.LOADING)
}