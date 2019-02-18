package net.hdhuu.splee.postscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_post.*
import net.hdhuu.domain.model.Post
import net.hdhuu.splee.R
import net.hdhuu.splee.SplitMessage
import net.hdhuu.splee.home.model.PostState
import net.hdhuu.splee.postscreen.model.PostNewMessageState
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class PostActivity : AppCompatActivity() {
    private val mViewModel: PostScreenViewModel by viewModel()
    private val splitter : SplitMessage by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        registerViewModel()
    }

    private fun registerViewModel() {
        btnPost.setOnClickListener {
            onSubmitClicked()
        }
        mViewModel.content.observe(this,
            Observer<String> {
                postContent.setText(it)
            })

        mViewModel.getLiveDAta().observe(this,
            Observer<PostNewMessageState> {
                if (it != null) this.handleDataState(it) })    }

    private fun handleDataState(postState: PostNewMessageState) {
        when (postState) {
            is PostNewMessageState.Loading -> {
                showLoading()
            }
            is PostNewMessageState.Success -> {
                showViewOnsuccess(postState.data)
            }
            is PostNewMessageState.Error ->  {
//                showErrorView(postState.errorMessage)
            }
        }
    }

    private fun showViewOnsuccess(data: Boolean?) {
        finish()
    }

    private fun showLoading() {
        loadingPostView.visibility = View.VISIBLE
    }

    fun onSubmitClicked(){
        val message = postContent.text.toString().trim()
        if (message.isEmpty()){
            Toast.makeText(this,getString(R.string.empty_message_note),Toast.LENGTH_SHORT).show()
            return;
        }
        if (isMoreThan50Characters(message)){
            Toast.makeText(this,getString(R.string.too_long_message),Toast.LENGTH_SHORT).show()
            return
        }

        if (message.length <50){
            mViewModel.postMessage(postContent.text.toString())
        }else{
           val messages = splitter.splitMessages(message)
            messages.forEach {it->
                Log.e("Message: ", it)
            }
            mViewModel.postMultiMessages(messages)

        }
    }

    private fun isMoreThan50Characters(s: String): Boolean {
        val arr = s.split(" ")

        arr.forEach { ss ->
            if (ss.length>50){
                return true
            }
        }
        return false
    }
}
