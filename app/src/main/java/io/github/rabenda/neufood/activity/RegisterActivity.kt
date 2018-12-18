package io.github.rabenda.neufood.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.github.rabenda.neufood.R
import io.github.rabenda.neufood.bean.RegisterBean
import io.github.rabenda.neufood.listener.TListener
import io.github.rabenda.neufood.model.RegisterModel
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity: AppCompatActivity() {

    val TAG = "RegisterActivity"

    val registerModel = RegisterModel()

    val registerListener = object : TListener<RegisterBean> {
        override fun onResponse(t: RegisterBean) {
            Log.d(TAG, "onResponse")
            when (t.success) {
                "1" -> {
                    toast("注册成功")
                    finish()
                }
                else -> {
                    toast("注册失败")
                }
            }
        }

        override fun onFail(msg: String) {
            Log.d(TAG, "onFailed")
            toast("注册失败")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register_button.setOnClickListener {
            Log.d(TAG, "registerButtonSetClickListener")
            registerModel.getRegisterResult(
                    register_username.text.toString(),
                    register_password.text.toString(),
                    register_mobilenum.text.toString(),
                    register_address.text.toString(),
                    register_comment.text.toString(),
                    registerListener
            )
        }
    }
}