package com.android.cowork.admin.ui.comment.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.cowork.admin.R
import com.android.cowork.admin.model.CommentData
import com.android.cowork.admin.ui.comment.CommentContact
import com.android.cowork.admin.ui.comment.holder.CommentHolder
import kotlinx.android.synthetic.main.item_comment.view.*

class CommentAdapter(private var something: List<CommentData>, private var view: CommentContact.View)
    : RecyclerView.Adapter<CommentHolder>() {

    override fun getItemViewType(position: Int): Int = R.layout.item_comment

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHolder
            = CommentHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))

    override fun getItemCount(): Int = something.size

    override fun onBindViewHolder(holder: CommentHolder, position: Int) {
        holder.onBind(something[position])
        holder.itemView.btnDelete.setOnClickListener {
            view.onCommentDelete(something[position].id.toString())
        }
    }

    fun setItem(arr: List<CommentData>?) {
        arr?.let { this.something = arr }
        notifyDataSetChanged()
    }
}