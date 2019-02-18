package net.hdhuu.splee.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.hdhuu.domain.model.Post

/**
 * Created by Huu Hoang on 2/17/19.
 */
class MainViewModel: ViewModel(){
    val listPosts: MutableLiveData<List<Post>> = MutableLiveData()

    init {
        listPosts.value = emptyList()
    }

    fun updateData(listPosts: List<Post>) {
        this.listPosts.value = listPosts
    }
}