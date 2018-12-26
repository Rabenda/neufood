package com.rabenda.network

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item.view.*

class Adapter(val dataList: List<ShopBean>): RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder =
        ViewHolder(View.inflate(p0.context, R.layout.item, null))


    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.itemView.item_tv.setText("String: ${dataList[p1].shopname}")
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}