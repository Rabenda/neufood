package io.github.rabenda.neufood.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import io.github.rabenda.neufood.R
import io.github.rabenda.neufood.activity.FoodActivity
import io.github.rabenda.neufood.bean.FoodBean
import io.github.rabenda.neufood.server.Server
import kotlinx.android.synthetic.main.food_item.view.*
import org.jetbrains.anko.startActivity

class FoodListAdapter(var foods: List<FoodBean>) : RecyclerView.Adapter<FoodListAdapter.ViewHolder>() {
    override fun getItemCount(): Int = foods.size

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder =
            ViewHolder(View.inflate(p0.context, R.layout.food_item, null))

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val food = foods[p1]
        with(p0.itemView) {
            Picasso.get().load(Server.BaseUrl + food.pic).into(iv_foodimage)
            tv_foodname.text = food.foodname
            tv_intro.text = food.intro
            tv_price.text = "￥${food.price}"
            if (food.recommand == "0")
                tv_recommand.visibility = View.INVISIBLE
            setOnClickListener {
                context.startActivity<FoodActivity>("food_id" to food.food_id)
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}