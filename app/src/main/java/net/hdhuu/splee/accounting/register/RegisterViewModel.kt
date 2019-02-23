package net.hdhuu.splee.accounting.register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.observers.DisposableObserver
import net.hdhuu.domain.model.Post
import net.hdhuu.domain.usecase.account.RegisterUseCase

/**
 * Created by Huu Hoang on 2/17/19.
 */
class RegisterViewModel(
    val registerUseCase: RegisterUseCase): ViewModel(){
    val listPosts: MutableLiveData<Boolean> = MutableLiveData()

    init {
        listPosts.value = false
    }

    fun register(userName:String, phone:String, password:String) {
        val params = RegisterUseCase.Params(userName,phone,password)
        registerUseCase.run(params,object :DisposableObserver<Boolean>(){
            override fun onComplete() {
                Log.e("HUUHOANG: LOGIN: ", "complete")
            }

            override fun onNext(t: Boolean) {
                Log.e("HUUHOANG: LOGIN: ", "onNext")
            }

            override fun onError(e: Throwable) {
                Log.e("HUUHOANG: LOGIN: ", "onError")
            }

        })
    }
}