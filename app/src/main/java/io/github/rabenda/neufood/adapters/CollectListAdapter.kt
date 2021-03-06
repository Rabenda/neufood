package io.github.rabenda.neufood.adapters


import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import io.github.rabenda.neufood.R
import io.github.rabenda.neufood.activity.FoodActivity
import io.github.rabenda.neufood.activity.StoreActivity
import io.github.rabenda.neufood.bean.CollectListBean
import io.github.rabenda.neufood.server.Server
import kotlinx.android.synthetic.main.collect_item.view.*
import org.jetbrains.anko.startActivity


class CollectListAdapter(var shows: List<CollectListBean>) : RecyclerView.Adapter<CollectListAdapter.ViewHolder>() {
    override fun getItemCount(): Int = shows.size

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder =
            ViewHolder(View.inflate(p0.context, R.layout.collect_item, null))

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val show = shows[p1]
        if (show.flag == "0")
            with(p0.itemView) {
                tv_collectshopname.text = show.shopname
                tv_collectaddr.text = show.address
                Picasso.get().load(Server.BaseUrl + show.pic).into(iv_collect)
                setOnClickListener {
                    context!!.startActivity<StoreActivity>(
                            "shop_id" to show.shop_id!!,
                            "shopname" to show.shopname!!
                    )
                }
            }
        else
            with(p0.itemView) {
                tv_collectshopname.text = show.foodname
                tv_collectaddr.text = show.address
                tv_collectprice.text = "￥${show.price}"
                Picasso.get().load(Server.BaseUrl + show.pic).into(iv_collect)
                setOnClickListener { context.startActivity<FoodActivity>("food_id" to show.food_id!!) }
            }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}