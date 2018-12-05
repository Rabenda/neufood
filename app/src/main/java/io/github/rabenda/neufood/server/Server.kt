package io.github.rabenda.neufood.server

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.rabenda.neufood.bean.StoreBean
import java.net.URL

object Server {
    const val SERVER_URL = "http://172.24.10.175:8080/foodService/"
    fun getStoreList() = Gson().fromJson<List<StoreBean>>(
            URL(SERVER_URL + "getAllShops.do").readText(),
            object : TypeToken<List<StoreBean>>() {}.type
    )
}
