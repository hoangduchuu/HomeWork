package net.hdhuu.domain.usecase

import io.reactivex.Observable
import net.hdhuu.domain.base.BaseUseCase
import net.hdhuu.domain.repository.PostRepository
import net.hdhuu.domain.scheduler.PostExecutionThread
import net.hdhuu.domain.scheduler.ThreadExecutor

/**
 * Created by Huu Hoang on 2/17/19.
 */

open class UpdateMessageUseCase(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    val postRepository: PostRepository
) : BaseUseCase<UpdateMessageUseCase.Params, Boolean>(threadExecutor, postExecutionThread) {

    /**
     * @param : Parameters
     */
    override fun buildUseCaseObservable(params: Params): Observable<Boolean> {
        return postRepository.updateMessage(params.postID,params.message)
    }

    data class Params(var postID:Int, var message:String)

}