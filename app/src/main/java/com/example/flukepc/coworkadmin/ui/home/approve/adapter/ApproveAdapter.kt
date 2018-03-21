package com.example.flukepc.coworkadmin.ui.home.approve.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.flukepc.coworkadmin.R
import com.example.flukepc.coworkadmin.model.CoWorkDetail
import com.example.flukepc.coworkadmin.ui.home.approve.holder.ApproveHolder

@Suppress("CAST_NEVER_SUCCEEDS")
class ApproveAdapter(private var something : List<CoWorkDetail>) : RecyclerView.Adapter<ApproveHolder>(){

    fun setItem(arr : List<CoWorkDetail>?){
        arr?.let { this.something = arr }
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_co_work_confirm

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApproveHolder
            = ApproveHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))

    override fun getItemCount(): Int = something.size

    override fun onBindViewHolder(holder: ApproveHolder, position: Int) = holder.onBind(something.get(position))
}