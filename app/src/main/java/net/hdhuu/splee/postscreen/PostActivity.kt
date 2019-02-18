package net.hdhuu.splee.postscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_post.*
import net.hdhuu.splee.R
import net.hdhuu.splee.SplitMessage
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

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
    }

    fun onSubmitClicked(){
        val message = postContent.text.toString().trim()

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
}
