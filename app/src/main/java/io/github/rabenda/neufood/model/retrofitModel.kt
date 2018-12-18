package io.github.rabenda.neufood.model

import io.github.rabenda.neufood.listener.ListListener
import io.github.rabenda.neufood.server.Server
import io.github.rabenda.neufood.service.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import io.github.rabenda.neufood.listener.TListener


object retrofitModel {
    val retrofit = Retrofit.Builder().baseUrl(Server.BaseUrl).addConverterFactory(GsonConverterFactory.create()).build()
    var service = retrofit.create(Service::class.java)

    fun <T> callEnqueue(call: Call<T>, tListener: TListener<T>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        tListener.onResponse(body)
                        return
                    }
                }
                val errorMessage = response.errorBody()?.toString()
                if (errorMessage != null) {
                    tListener.onFail(errorMessage)
                } else {
                    tListener.onFail("Unknown Error")
                }


            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                tListener.onFail("Error")
            }
        })

    }

    fun <T> callEnqueueList(call: Call<List<T>>, listListener: ListListener<T>) {
        call.enqueue(object : Callback<List<T>> {
            override fun onResponse(call: Call<List<T>>, response: Response<List<T>>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        listListener.onResponse(body)
                        return
                    }

                }
                val errorMessage = response.errorBody()?.toString()
                if (errorMessage != null) {
                    listListener.onFail(errorMessage)
                } else {
                    listListener.onFail("Unknown Error")
                }
            }

            override fun onFailure(call: Call<List<T>>, t: Throwable) {
                listListener.onFail("Unknown Error")
            }
        })
    }
}