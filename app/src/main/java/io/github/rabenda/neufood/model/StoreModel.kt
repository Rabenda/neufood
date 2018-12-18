package io.github.rabenda.neufood.model

import io.github.rabenda.neufood.bean.StoreBean
import io.github.rabenda.neufood.iface.StoreIFace
import io.github.rabenda.neufood.listener.ListListener

class StoreModel : StoreIFace {
    override fun getRegisterResult(listListener: ListListener<StoreBean>) {
        val call = retrofitModel.service.getShopList()
        retrofitModel.callEnqueueList(call, listListener)
    }
}