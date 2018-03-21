package com.example.flukepc.coworkadmin.ui.comment.holder

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.flukepc.coworkadmin.model.CommentData
import com.example.flukepc.coworkadmin.ui.confirm.ConfirmActivity
import kotlinx.android.synthetic.main.item_comment.view.*

class CommentHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun onBind(get: CommentData) {
        itemView.apply {
            commentMessage.text = get.comment
            tvStatus.text = get.status
            btnDelete.setOnClickListener {
                itemView.context.startActivity(Intent(
                        itemView.context, ConfirmActivity::class.java
                ).putExtra("key",get.id))
            }
        }
    }
}