package com.example.flukepc.coworkadmin.ui.home.comment.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.flukepc.coworkadmin.R
import com.example.flukepc.coworkadmin.model.CoWorkDetail
import com.example.flukepc.coworkadmin.ui.home.comment.holder.CommentHolder

class CommentAdapter(private var something: List<CoWorkDetail>) : RecyclerView.Adapter<CommentHolder>() {

    fun setItem(arr: List<CoWorkDetail>?) {
        arr?.let { this.something = arr }
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_co_work

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHolder
            = CommentHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))

    override fun getItemCount(): Int = something.size

    override fun onBindViewHolder(holder: CommentHolder, position: Int) = holder.onBind(something.get(position))
}