package io.github.rabenda.neufood.model

import android.util.Log
import android.widget.Toast
import io.github.rabenda.neufood.server.Server
import io.github.rabenda.neufood.service.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import io.github.rabenda.neufood.listener.TListener

class RetrofitBaseModel {
    companion object {
        val retrofit = Retrofit.Builder().baseUrl(Server.SERVER_URL).addConverterFactory(GsonConverterFactory.create()).build()
        var service = retrofit.create(Service::class.java)

        fun <T> callEnqueue(call: Call<T>, tListener: TListener<T>) {
            call.enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    if (response.isSuccessful && response.body() != null) {
                        tListener.onResponse(response.body()!!)
                    } else {
                        tListener.onFail("error")
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    tListener.onFail("error1")

                }
            })

        }
    }
}