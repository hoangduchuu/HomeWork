package net.hdhuu.splee.postscreen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.observers.DisposableObserver
import net.hdhuu.domain.usecase.PostMessageUseCase
import net.hdhuu.domain.usecase.PostMultipleMessageUseCase

/**
 * Created by Huu Hoang on 2/19/19.
 */
class PostScreenViewModel(val postMessageUseCase: PostMessageUseCase, val postMultipleMessageUseCase: PostMultipleMessageUseCase) : ViewModel() {
    val content: MutableLiveData<String> = MutableLiveData()

    init {
        content.value = ""
    }

    fun postMessage(message:String){
        postMessageUseCase.run(message,object :DisposableObserver<Boolean>(){
            override fun onComplete() {

            }

            override fun onNext(t: Boolean) {
            }

            override fun onError(e: Throwable) {
            }

        })
    }

    fun postMultiMessages(messages: List<String>){
        postMultipleMessageUseCase.run(messages,object :DisposableObserver<Boolean>(){
            override fun onComplete() {
                // tood FInish screen
            }

            override fun onNext(t: Boolean) {

            }

            override fun onError(e: Throwable) {

            }

        })
    }
}
