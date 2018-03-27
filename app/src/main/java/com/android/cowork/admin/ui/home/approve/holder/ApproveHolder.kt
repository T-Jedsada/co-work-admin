package com.android.cowork.admin.ui.home.approve.holder

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.android.cowork.admin.load
import com.android.cowork.admin.model.CoWorkDetail
import com.android.cowork.admin.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.item_co_work_confirm.view.*

class ApproveHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun onBind(detail: CoWorkDetail) {
        itemView.apply {
            tvCoPoster.load(detail.gallery?.poster)
            nameCoWork.text = detail.name
            status.text = detail.approve.toString()
            setOnClickListener {
                context.startActivity(Intent(context, DetailActivity::class.java)
                        .putExtra(DetailActivity.Key, detail))
            }
        }
    }
}