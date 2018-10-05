package io.github.rabenda.neufood.iface

import io.github.rabenda.neufood.bean.LoginBean
import io.github.rabenda.neufood.listener.TListener

interface LoginIFace<T> {
    fun getLoginResult(username: String,
                       password: String,
                       tListener: TListener<LoginBean>)
}