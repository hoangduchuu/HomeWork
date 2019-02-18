package net.hdhuu.domain.usecase

import io.reactivex.Observable
import net.hdhuu.domain.base.BaseUseCase
import net.hdhuu.domain.repository.PostRepository
import net.hdhuu.domain.scheduler.PostExecutionThread
import net.hdhuu.domain.scheduler.ThreadExecutor

/**
 * Created by Huu Hoang on 2/17/19.
 */

open class DeleteMessageUseCase(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    val postRepository: PostRepository
) : BaseUseCase<String, Boolean>(threadExecutor, postExecutionThread) {

    /**
     * @param : is post message;
     */
    override fun buildUseCaseObservable(params: String): Observable<Boolean> {
        return postRepository.deleteMessage(Integer.valueOf(params))
    }
}