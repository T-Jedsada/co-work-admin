package com.android.cowork.admin.ui.home.approve.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.cowork.admin.R
import com.android.cowork.admin.model.CoWorkDetail
import com.android.cowork.admin.ui.home.approve.ApproveContact
import com.android.cowork.admin.ui.home.approve.ApproveFragment
import com.android.cowork.admin.ui.home.approve.holder.ApproveHolder
import kotlinx.android.synthetic.main.item_co_work_confirm.view.*

@Suppress("CAST_NEVER_SUCCEEDS")
class ApproveAdapter(private var something: List<CoWorkDetail>, private val view: ApproveContact.View?)
    : RecyclerView.Adapter<ApproveHolder>() {

    fun setItem(arr: List<CoWorkDetail>?) {
        arr?.let { this.something = arr }
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_co_work_confirm

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApproveHolder = ApproveHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))

    override fun getItemCount(): Int = something.size

    override fun onBindViewHolder(holder: ApproveHolder, position: Int) {
        holder.onBind(something[position])
        holder.itemView.apply {
            btnEject.setOnClickListener { view?.onJudgeAction(something[position].id, ApproveFragment.REJECT_KEY_OPTION) }
            btnApprove.setOnClickListener { view?.onJudgeAction(something[position].id, ApproveFragment.APPROVE_KEY_OPTION) }
        }
    }
}