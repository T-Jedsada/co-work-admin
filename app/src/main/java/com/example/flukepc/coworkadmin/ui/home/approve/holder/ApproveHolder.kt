package com.example.flukepc.coworkadmin.ui.home.approve.holder
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.flukepc.coworkadmin.load
import com.example.flukepc.coworkadmin.model.CoWorkDetail
import com.example.flukepc.coworkadmin.ui.confirm.ConfirmActivity
import com.example.flukepc.coworkadmin.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.item_co_work_confirm.view.*

class ApproveHolder(itemView: View?) : RecyclerView.ViewHolder(itemView){

    fun onBind(detail: CoWorkDetail) {
        itemView.apply {
            tvCoPoster.load(detail.gallery?.poster)
            nameCoWork.text=detail.name
            status.text= detail.approve.toString()
            itemView.setOnClickListener {
                itemView.context.startActivity(Intent(
                        itemView.context, DetailActivity::class.java
                ).putExtra(DetailActivity.Key, detail))
            }
            btnApprove.setOnClickListener {
                itemView.context.startActivity(Intent(
                        itemView.context, ConfirmActivity::class.java
                ).putExtra("approve-co",detail.id))

            }
            btnEject.setOnClickListener {
                itemView.context.startActivity(Intent(
                        itemView.context, ConfirmActivity::class.java
                ).putExtra("reject-co",detail.id))
            }

        }
    }
}