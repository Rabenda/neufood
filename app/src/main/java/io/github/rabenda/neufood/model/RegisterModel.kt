package io.github.rabenda.neufood.model

import io.github.rabenda.neufood.bean.RegisterBean
import io.github.rabenda.neufood.iface.RegisterIFace
import io.github.rabenda.neufood.listener.TListener

class RegisterModel: RetrofitBaseModel(), RegisterIFace<RegisterBean> {
    override fun getRegisterResult(username: String, password: String, mobilenum: String, address: String, comment: String, tListener: TListener<RegisterBean>) {
        val call = service.userRegister(username, password, mobilenum, address, comment)
        callEnqueue(call, tListener)
    }
}