package io.github.rabenda.neufood.iface

import io.github.rabenda.neufood.bean.RegisterBean
import io.github.rabenda.neufood.listener.TListener

interface RegisterIFace {
    fun getRegisterResult(username: String, password: String, mobilenum: String, address: String, comment: String, tListener: TListener<RegisterBean>)
}