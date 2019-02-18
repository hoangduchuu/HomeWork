package net.hdhuu.splee.postscreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import net.hdhuu.domain.usecase.PostMessageUseCase
import net.hdhuu.domain.usecase.PostMultipleMessageUseCase
import net.hdhuu.splee.home.model.PostState
import net.hdhuu.splee.postscreen.model.PostNewMessageState

/**
 * Created by Huu Hoang on 2/19/19.
 */
class PostScreenViewModel(val postMessageUseCase: PostMessageUseCase, val postMultipleMessageUseCase: PostMultipleMessageUseCase) : ViewModel() {
    val content: MutableLiveData<String> = MutableLiveData()
    private val liveData: MutableLiveData<PostNewMessageState> = MutableLiveData()
    private var disposable: Disposable? = null

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }

    fun getLiveDAta(): LiveData<PostNewMessageState> {
        return liveData
    }

    init {
        content.value = ""
    }

    fun postMessage(message:String){
        postMessageUseCase.run(message,object :DisposableObserver<Boolean>(){
            override fun onComplete() {
                liveData.postValue(PostNewMessageState.Success(true))

            }

            override fun onNext(t: Boolean) {
            }

            override fun onError(e: Throwable) {
                liveData.postValue(PostNewMessageState.Error(e.localizedMessage))
            }

        })
    }

    fun postMultiMessages(messages: List<String>){
        postMultipleMessageUseCase.run(messages,object :DisposableObserver<Boolean>(){
            override fun onComplete() {
                liveData.postValue(PostNewMessageState.Success(true))
            }

            override fun onNext(t: Boolean) {

            }

            override fun onError(e: Throwable) {
                liveData.postValue(PostNewMessageState.Error(e.localizedMessage))

            }

        })
    }
}
