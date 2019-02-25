package net.hdhuu.splee.home.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import net.hdhuu.domain.model.Post
import net.hdhuu.domain.usecase.DeleteMessageUseCase
import net.hdhuu.domain.usecase.GetPostUseCase
import net.hdhuu.domain.usecase.account.RegisterUseCase
import net.hdhuu.splee.di.registerModule

class MainViewModel(
    val getPosts: GetPostUseCase,
    val deleteMessageUseCase: DeleteMessageUseCase,
    val registerUseCase: RegisterUseCase
) : ViewModel() {

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
        registerUseCase.run(RegisterUseCase.Params("hoangduchuuvn${id}@gmail.com",id,"abcdef12345"), object : DisposableObserver<Boolean>() {
            override fun onComplete() {
                liveData.postValue(PostState.Success(liveData.value?.data!!))
            }

            override fun onNext(t: Boolean) {
            }

            override fun onError(e: Throwable) {
            }

        })
    }
}