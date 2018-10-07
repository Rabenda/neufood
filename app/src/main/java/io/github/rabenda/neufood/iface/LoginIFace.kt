package io.github.rabenda.neufood.iface

import io.github.rabenda.neufood.bean.LoginBean
import io.github.rabenda.neufood.listener.TListener

interface LoginIFace {
    fun getLoginResult(username: String,
                       password: String,
                       tListener: TListener<LoginBean>)
}