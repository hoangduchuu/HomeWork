package net.hdhuu.splee.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import net.hdhuu.splee.R
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import net.hdhuu.domain.model.Post
import net.hdhuu.splee.home.model.MainViewModel
import net.hdhuu.splee.home.model.PostState
import net.hdhuu.splee.postscreen.PostActivity


class MainActivity : AppCompatActivity(){

    val mainViewModel: MainViewModel by viewModel()

    val mainPostAdapter: MainPostAdapter by inject()
    val firebaseDatabase = FirebaseDatabase.getInstance();
    val databaseReference = firebaseDatabase.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRV()

    }

    override fun onStart() {
        super.onStart()
        registerLiveDataListenner()

    }

    private fun setUpRV() {
        mainPostAdapter.postClickListener = projectListener
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = mainPostAdapter
    }


    private fun registerLiveDataListenner() {
        mainViewModel.getPost().observe(this,
            Observer<PostState> {
                if (it != null) this.handleDataState(it) })
        Handler().postDelayed({
            mainViewModel.getPosts()
        },0)// clone loading
    }


    private fun updateListView(posts: List<Post>) {
        mainPostAdapter.posts = posts
        mainPostAdapter.notifyDataSetChanged()
    }

    private val projectListener = object : PostClickListener {
        override fun onItemClicked(id: String) {

            mainViewModel.remove(id)
//            databaseReference.child(id).setValue(mainViewModel.getPost().value,"b");
        }

    }
    
    fun startPostScreen(view:View){
        startActivity( Intent(this, PostActivity::class.java))
    }
    //region setupView
    private fun handleDataState(postState: PostState) {
        when (postState) {
            is PostState.Loading -> {
                showLoading()
            }
            is PostState.Success -> {
                showViewOnsuccess(postState.data)
            }
            is PostState.Error ->  {
                showErrorView(postState.errorMessage)
            }
        }
    }

    private fun showErrorView(errorMessage: String?) {
        //FIXME, show error message to errorView
        errorView.visibility = View.VISIBLE
        rv.visibility = View.GONE
        emptyDataView.visibility = View.GONE
        loadingView.visibility = View.GONE
    }

    private fun showLoading() {
        loadingView.visibility = View.VISIBLE
        errorView.visibility = View.GONE
        rv.visibility = View.GONE
        emptyDataView.visibility = View.GONE

    }

    private fun showViewOnsuccess(data: List<Post>?) {
        Log.d("DATA: ", data.toString())
        if (data!= null && data.isNotEmpty()) {
            showListView()
            updateListView(data)
        }else{
            showEmptyData()
        }
    }
    private fun showEmptyData() {
        emptyDataView.visibility = View.VISIBLE
        rv.visibility = View.GONE
        loadingView.visibility = View.GONE
        errorView.visibility = View.GONE
    }

    private fun showListView() {
        rv.visibility = View.VISIBLE
        loadingView.visibility = View.GONE
        errorView.visibility = View.GONE
        emptyDataView.visibility = View.GONE
    }

    //endregion


}
