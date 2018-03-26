package com.example.flukepc.coworkadmin.ui.home.comment.holder

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.flukepc.coworkadmin.load
import com.example.flukepc.coworkadmin.model.CoWorkDetail
import com.example.flukepc.coworkadmin.ui.comment.CommentActivity
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