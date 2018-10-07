package io.github.rabenda.neufood.iface

import io.github.rabenda.neufood.bean.StoreBean
import io.github.rabenda.neufood.listener.ListListener

interface StoreIFace {
    fun getRegisterResult(listListener: ListListener<StoreBean>)
}