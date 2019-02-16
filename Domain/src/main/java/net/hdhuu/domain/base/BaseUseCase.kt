package net.hdhuu.domain.base

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import net.hdhuu.domain.scheduler.PostExecutionThread
import net.hdhuu.domain.scheduler.ThreadExecutor


/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 *
 * By convention each UseCase implementation will return the result using a [DisposableObserver]
 * that will execute its job in a background thread and will post the result in the UI thread.
 */
/**
 * Abstract class for a UseCase that returns an instance of a [Single].
 */
abstract class BaseUseCase<INPUT_DATA, RESPONSE_DATA> constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {

    /**
     * The thread to execute the business logic. It will be the backend thread to prevent locking
     * main thread.
     */
    var mThreadExecutor = threadExecutor;
    /**
     * The thread that receive result after executing business logic. It maybe main thread if we
     * need to update UI or any thread. It's belong to specific uses
     */
    var mPostExecutionThread = postExecutionThread

    /**
     * Use this to make the use-case cancellable anytime.
     */
    private val disposables = CompositeDisposable()

    /**
     * Builds an [Observable] which will be used when executing the current [UseCase].
     * Any use-case must override this method to provide the entity of that use-case.
     */
    abstract fun buildUseCaseObservable(params: INPUT_DATA): Observable<RESPONSE_DATA>


    /**
     * This is the most important method of an use-case. We mix everything here to execute the
     * current use-case.

     * @param observer [DisposableObserver] which will be listening to the observable build
     * *                 by [.buildUseCaseObservable] ()} method.
     * *
     * @param params   Parameters (Optional) used to build/execute this use case.
     */
    fun run(params: INPUT_DATA, observer: DisposableObserver<RESPONSE_DATA>? = null) {
        val observable = run(params)
        if (observer != null)
            addDisposable(observable.subscribeWith(observer))
        else
            observable.subscribe()
    }

    fun run(params: INPUT_DATA): Observable<RESPONSE_DATA> {
        return buildUseCaseObservable(params).compose(applyThread())
    }

    fun autoRun(params: INPUT_DATA) {
        run(params).subscribe()
    }

    fun applyThread(): ObservableTransformer<RESPONSE_DATA, RESPONSE_DATA> {
        return ObservableTransformer { stream ->
            stream.subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.scheduler, true)
        }
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

}