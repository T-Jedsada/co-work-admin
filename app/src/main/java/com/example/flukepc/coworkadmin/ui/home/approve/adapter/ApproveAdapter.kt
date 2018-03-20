package com.example.flukepc.coworkadmin.ui.home.approve.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.flukepc.coworkadmin.R
import com.example.flukepc.coworkadmin.ui.home.approve.holder.ApproveHolder

class ApproveAdapter(private var something : List<String>) : RecyclerView.Adapter<ApproveHolder>(){

    fun setItem(arr : List<String>?){
        arr?.let { this.something = arr }
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_co_work_confirm

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApproveHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ApproveHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}