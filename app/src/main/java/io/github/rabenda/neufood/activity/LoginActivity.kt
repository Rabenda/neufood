package io.github.rabenda.neufood.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.beardedhen.androidbootstrap.TypefaceProvider
import io.github.rabenda.neufood.R
import io.github.rabenda.neufood.bean.LoginBean
import io.github.rabenda.neufood.listener.TListener
import kotlinx.android.synthetic.main.activity_login.*
import io.github.rabenda.neufood.model.*
import org.jetbrains.anko.toast

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {

    val TAG = "LoginActivity"

    val loginModel = LoginModel()

    val loginListener = object : TListener<LoginBean> {
        override fun onResponse(t: LoginBean) {
            Log.d(TAG, "onResponse")
            when (t.userid) {
                "0" -> {
                    toast("登录失败")
                }
                else -> {
                    toast("登录成功")
                    getSharedPreferences("NeuFood",Activity.MODE_PRIVATE).edit().putString("user_id", t.userid).commit()
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                }
            }
        }

        override fun onFail(msg: String) {
            Log.d(TAG, "onFailed")
            toast("登录失败")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TypefaceProvider.registerDefaultIconSets()
        setContentView(R.layout.activity_login)

        login_button.setOnClickListener {
            Log.i(TAG, login_username.text.toString())
            Log.i(TAG, login_password.text.toString())
            loginModel.getLoginResult(login_username.text.toString(),
                    login_password.text.toString(),
                    loginListener
                    )

        }

        register_button.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

    }


}
