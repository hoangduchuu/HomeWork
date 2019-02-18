package net.hdhuu.splee.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*
import net.hdhuu.domain.model.Post
import net.hdhuu.splee.R

class MainPostAdapter : RecyclerView.Adapter<MainPostAdapter.ViewHolder>() {
    var postClickListener: PostClickListener? = null

    var posts: List<Post> = arrayListOf()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts[position]

        holder.tvContent?.text = post.content
        holder.tvCreateAt?.text = post.createAt.toString()
        holder.tvID?.text = post.id.toString()

        holder.itemView.setOnClickListener {
            postClickListener?.onItemClicked(post.id.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvContent = view.txtContent
        val tvCreateAt = view.txtCreatedAt
        val tvID = view.txtId
    }

}