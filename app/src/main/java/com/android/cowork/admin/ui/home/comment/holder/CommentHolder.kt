package com.android.cowork.admin.ui.home.comment.holder

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.android.cowork.admin.load
import com.android.cowork.admin.model.CoWorkDetail
import com.android.cowork.admin.ui.comment.CommentActivity
import kotlinx.android.synthetic.main.item_co_work_confirm.view.*

class CommentHolder (itemView: View?) : RecyclerView.ViewHolder(itemView){

    fun onBind(get: CoWorkDetail) {
        itemView.apply {
            tvCoPoster.load(get.gallery?.poster)
            nameCoWork.text=get.name
            itemView.setOnClickListener {
                itemView.context.startActivity(Intent(
                        itemView.context, CommentActivity::class.java
                ).putExtra("key",get.id))
            }
        }
    }
}