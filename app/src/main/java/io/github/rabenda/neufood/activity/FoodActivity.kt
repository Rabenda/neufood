package io.github.rabenda.neufood.activity

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.prime.dnuifood.Fragments.AddCartAlert
import com.prime.dnuifood.Fragments.InsertOrderAlert
import com.squareup.picasso.Picasso
import io.github.rabenda.neufood.R
import io.github.rabenda.neufood.adapters.CommentListAdaper
import io.github.rabenda.neufood.bean.FoodBean
import io.github.rabenda.neufood.server.Server
import kotlinx.android.synthetic.main.activity_food.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

class FoodActivity : AppCompatActivity() {

    private val foodId get() = intent.getStringExtra("food_id")
    private val share get() = getSharedPreferences("NeuFood", Activity.MODE_PRIVATE)
    private val userId get() = share.getString("user_id", "")
    private var menu: Menu? = null
    private lateinit var food: FoodBean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)
        rv_foodcomment.layoutManager = GridLayoutManager(this, 1)
        commentList()
        showfood()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        rb_insertorder.setOnClickListener { InsertOrderAlert(this, food, userId).create().show() }
        rb_addcart.setOnClickListener { AddCartAlert(this,userId,foodId).create().show() }
        fabt_shopcar.setOnClickListener { startActivity<ShopCarActivity>() }
    }


    private fun updateCollect() = doAsync {
        val result = Server.isCollected(userId, foodId, "0")
        uiThread {
            if (result.collected == "1")
                menu!!.findItem(R.id.isCollected).setIcon(R.drawable.ic_collected)
            else
                menu!!.findItem(R.id.isCollected).setIcon(R.drawable.ic_notcollected)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.iscollected, menu)
        this.menu = menu
        updateCollect()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.isCollected -> doAsync {
                val result = Server.collectFood(userId, foodId)
                uiThread {
                    if (result.success == "1")
                        updateCollect()
                }
            }
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun commentList() = doAsync {
        val commentlist = Server.getAllCommentsByFood(foodId)
        uiThread {
            rv_foodcomment.adapter = CommentListAdaper(userId, commentlist.toMutableList())
        }
    }

    fun showfood() = doAsync {
        food = Server.getFoodById(foodId)
        uiThread {
            Picasso.get().load(Server.BaseUrl + food.pic).into(iv_foodimage)
            tv_foodname.text = food.foodname
            tv_foodprice.text = "￥${food.price}"
            tv_intro.text = food.intro
            it.title = food.foodname
        }
    }

}
