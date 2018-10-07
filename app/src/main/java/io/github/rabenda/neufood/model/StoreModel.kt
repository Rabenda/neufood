package io.github.rabenda.neufood.model

import io.github.rabenda.neufood.bean.RegisterBean
import io.github.rabenda.neufood.bean.StoreBean
import io.github.rabenda.neufood.iface.StoreIFace
import io.github.rabenda.neufood.listener.ListListener
import io.github.rabenda.neufood.listener.TListener

class StoreModel: StoreIFace {
    override fun getRegisterResult(listListener: ListListener<StoreBean>) {
        val call = RetrofitBaseModel.service.getShopList()
        RetrofitBaseModel.callEnqueueList(call, listListener)
    }
}