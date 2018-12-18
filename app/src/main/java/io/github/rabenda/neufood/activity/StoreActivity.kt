package io.github.rabenda.neufood.activity

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import io.github.rabenda.neufood.R
import io.github.rabenda.neufood.adapters.FoodListAdapter
import io.github.rabenda.neufood.server.Server
import kotlinx.android.synthetic.main.activity_store.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class StoreActivity : AppCompatActivity() {

    private val storeId get() = intent.getStringExtra("shop_id")
    private val storeName get() = intent.getStringExtra("shopname")
    private val share get() = getSharedPreferences("NeuFood", Activity.MODE_PRIVATE)
    private val userId get() = share.getString("user_id", "")
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)
        title = storeName
        store_RV.layoutManager = GridLayoutManager(this, 2)

        doAsync {
            var foodList = Server.getFoodByShop(storeId)
            uiThread {
                store_RV.adapter = FoodListAdapter(foodList)
            }
        }

    }


    private fun updateCollect() = doAsync {
        val result = Server.isCollected(userId, storeId, "0")
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
        if (item?.itemId == R.id.isCollected)
            doAsync {
                val result = Server.collectShop(userId, storeId)
                uiThread {
                    if (result.success == "1")
                        updateCollect()
                }
            }
        return super.onOptionsItemSelected(item)
    }

}
