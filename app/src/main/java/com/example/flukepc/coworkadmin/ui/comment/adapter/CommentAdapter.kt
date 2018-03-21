package com.example.flukepc.coworkadmin.ui.comment.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.flukepc.coworkadmin.R
import com.example.flukepc.coworkadmin.model.CommentData
import com.example.flukepc.coworkadmin.ui.comment.holder.CommentHolder

class CommentAdapter(private var something: List<CommentData>) : RecyclerView.Adapter<CommentHolder>() {

    fun setItem(arr: List<CommentData>?) {
        arr?.let { this.something = arr }
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_comment

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHolder
            = CommentHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))

    override fun getItemCount(): Int = something.size

    override fun onBindViewHolder(holder: CommentHolder, position: Int) =holder.onBind(something.get(position))
}