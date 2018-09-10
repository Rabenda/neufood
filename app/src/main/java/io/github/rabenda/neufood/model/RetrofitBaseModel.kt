package io.github.rabenda.neufood.model

import io.github.rabenda.neufood.server.Server
import io.github.rabenda.neufood.service.Service
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBaseModel {
    companion object {
        val retrofit = Retrofit.Builder().baseUrl(Server.SERVER_URL).addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create<Service>(Service::class.java)
    }

//    fun<T> callEnqueue(val call: Call<T>, )
}