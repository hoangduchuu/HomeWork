package net.hdhuu.domain.usecase

import io.reactivex.Observable
import net.hdhuu.domain.base.BaseUseCase
import net.hdhuu.domain.model.Post
import net.hdhuu.domain.repository.PostRepository
import net.hdhuu.domain.scheduler.PostExecutionThread
import net.hdhuu.domain.scheduler.ThreadExecutor

open class GetPostUseCase(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    val postRepository: PostRepository) : BaseUseCase<String, List<Post>>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: String): Observable<List<Post>> {
        return postRepository.getAllPosts()
    }
}