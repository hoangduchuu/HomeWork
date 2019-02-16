package net.hdhuu.splee.home

import android.util.Log
import io.reactivex.observers.DisposableObserver
import net.hdhuu.domain.model.Post
import net.hdhuu.domain.usecase.GetPostUseCase

class MainPresenter constructor( val getPostUseCase: GetPostUseCase, val view: MainContract.View) : MainContract.Presenter{

    override fun testPresenter() {
        getPostUseCase.run("",object : DisposableObserver<List<Post>>(){
            override fun onComplete() {
                view.testView()
            }

            override fun onNext(t: List<Post>) {
                t.forEach {it->Log.e("DI",it.content) }
            }

            override fun onError(e: Throwable) {

            }

        })
    }

}