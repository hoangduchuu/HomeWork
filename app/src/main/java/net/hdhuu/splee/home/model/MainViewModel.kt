package net.hdhuu.splee.home.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import net.hdhuu.domain.model.Post
import net.hdhuu.domain.usecase.DeleteMessageUseCase
import net.hdhuu.domain.usecase.GetPostUseCase

class MainViewModel(val getPosts: GetPostUseCase, val  deleteMessageUseCase: DeleteMessageUseCase) : ViewModel() {

    private val liveData: MutableLiveData<PostState> = MutableLiveData()
    private var disposable: Disposable? = null

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }

    fun getPost(): LiveData<PostState> {
        return liveData
    }

    fun getPosts() {
        liveData.postValue(PostState.Loading)
            getPosts.run("", object : DisposableObserver<List<Post>>() {
                override fun onComplete() {
                }

                override fun onNext(t: List<Post>) {
                    liveData.postValue(PostState.Success(t))
                }

                override fun onError(it: Throwable) {
                    liveData.postValue(PostState.Error(it.message))
                }

            })
    }

    fun remove(id: String) {
        deleteMessageUseCase.run(id,object :DisposableObserver<Boolean>(){
            override fun onComplete() {
                liveData.postValue(PostState.Success(liveData.value?.data!!))
            }

            override fun onNext(t: Boolean) {
            }

            override fun onError(e: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }
}