package net.hdhuu.splee.home.model

import net.hdhuu.domain.model.Post

sealed class PostState(val resourceState: ResourceState,
                       val data: List<Post>? = null,
                       val errorMessage: String? = null) {

    data class Success(private val posts: List<Post>): PostState(
        ResourceState.SUCCESS,
        posts)

    data class Error(private val message: String? = null): PostState(
        ResourceState.SUCCESS,
            errorMessage = message)

    object Loading: PostState(ResourceState.LOADING)
}