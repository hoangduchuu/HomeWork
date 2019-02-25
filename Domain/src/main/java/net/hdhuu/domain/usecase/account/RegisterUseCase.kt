package net.hdhuu.domain.usecase.account

import io.reactivex.Observable
import net.hdhuu.domain.base.BaseUseCase
import net.hdhuu.domain.repository.PostRepository
import net.hdhuu.domain.repository.UserRepository
import net.hdhuu.domain.scheduler.PostExecutionThread
import net.hdhuu.domain.scheduler.ThreadExecutor

/**
 * Created by Huu Hoang on 2/17/19.
 */

open class RegisterUseCase(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    val userRepository: UserRepository
) : BaseUseCase<RegisterUseCase.Params, Boolean>(threadExecutor, postExecutionThread) {

    /**
     * @param : is post message;
     */
    override fun buildUseCaseObservable(params: RegisterUseCase.Params): Observable<Boolean> {
        return userRepository.register(params.userName, params.phone, params.passWord)
    }

    class Params(val userName:String, val phone: String, val passWord:String)
}