package com.rabenda.network

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_rv.layoutManager = GridLayoutManager(this, 1)
        doAsync {
            val dataList = Gson().fromJson<List<ShopBean>>(URL("http://172.24.10.175:8080/foodService/getAllShops.do").readText(), object : TypeToken<List<ShopBean>>() {}.type)
            uiThread {
                main_rv.adapter = Adapter(dataList)
            }
        }
    }


}
