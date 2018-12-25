package io.github.rabenda.neufood.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.github.rabenda.neufood.R
import io.github.rabenda.neufood.server.Server
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class RegisterActivity : AppCompatActivity() {

    val TAG = "RegisterActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register_button.setOnClickListener {
            Log.d(TAG, "registerButtonSetClickListener")

            doAsync {
                val registerResult = Server.register(register_username.text.toString(),
                        register_password.text.toString(),
                        register_mobilenum.text.toString(),
                        register_address.text.toString(),
                        register_comment.text.toString())
                uiThread {
                    when (registerResult.success) {
                        "1" -> {
                            toast("注册成功")
                            finish()
                        }
                        "0" -> {
                            toast("注册失败")
                        }
                        else -> {
                            toast("Unknown Error in Register")
                        }
                    }
                }
            }


        }
    }
}