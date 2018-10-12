package io.github.rabenda.neufood.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

import  io.github.rabenda.neufood.bean.RegisterBean
import  io.github.rabenda.neufood.bean.LoginBean
import io.github.rabenda.neufood.bean.StoreBean

interface Service {

    @GET("userLogin.do")
    fun userLogin(@Query("username") username: String, @Query("userpass") userpass: String ): Call<LoginBean>

    @GET("userRegister.do")
    fun userRegister(@Query("username") username: String, @Query("userpass") password: String,
                     @Query("mobilenum") mobilenum: String, @Query("address") address: String,
                     @Query("comment") comment: String): Call<RegisterBean>

    @GET("getAllShops.do")
    fun getShopList(): Call<List<StoreBean>>



}