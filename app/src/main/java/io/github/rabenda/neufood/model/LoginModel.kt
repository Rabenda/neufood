package io.github.rabenda.neufood.model

import io.github.rabenda.neufood.bean.LoginBean
import io.github.rabenda.neufood.iface.LoginIFace
import io.github.rabenda.neufood.listener.TListener

class LoginModel: RetrofitBaseModel(), LoginIFace<LoginBean> {
    override fun getLoginResult(username: String, password: String, tListener: TListener<LoginBean>) {
        val call = service.userLogin(username, password)
        callEnqueue(call, tListener)
    }
}