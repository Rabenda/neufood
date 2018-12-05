package io.github.rabenda.neufood.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import io.github.rabenda.neufood.R
import io.github.rabenda.neufood.activity.StoreActivity
import io.github.rabenda.neufood.bean.StoreBean
import org.jetbrains.anko.startActivity
import io.github.rabenda.neufood.server.Server
import kotlinx.android.synthetic.main.store_item.view.*

class StoreAdapter(val storeList: List<StoreBean>): RecyclerView.Adapter<StoreAdapter.ViewHolder>() {
    override fun getItemCount(): Int = storeList.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val shop = storeList[p1]
        with(p0.itemView) {
            Picasso.get().load(Server.SERVER_URL + shop.pic).into(iv_shopimage)
            tv_shopname.setText(shop.shopname)
            tv_shopaddr.setText(shop.address)
            tv_shopcomment.setText(shop.comment)
            tv_shopintro.setText(shop.intro)
            tv_shophone.setText(shop.phonenum)
            rb_shoplevel.rating = shop.level
            setOnClickListener {
                context.startActivity<StoreActivity>("shopname" to shop.shopname, "shop_id" to shop.shop_id)
            }
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder = ViewHolder(View.inflate(p0.context, R.layout.store_item ,null))

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}