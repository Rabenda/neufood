package io.github.rabenda.neufood.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.beardedhen.androidbootstrap.TypefaceProvider
import io.github.rabenda.neufood.R
import io.github.rabenda.neufood.bean.LoginBean
import io.github.rabenda.neufood.server.Server
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {

    val TAG = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TypefaceProvider.registerDefaultIconSets()
        setContentView(R.layout.activity_login)

        login_button.setOnClickListener {
            Log.i(TAG, login_username.text.toString())
            Log.i(TAG, login_password.text.toString())

            doAsync {
                val loginResult = Server.login(login_username.text.toString(),
                        login_password.text.toString())
                uiThread {
                    when (loginResult.userid) {
                        "0" -> {
                            toast("登录失败")
                        }
                        else -> {
                            toast("登录成功")
                            getSharedPreferences("NeuFood", Activity.MODE_PRIVATE).edit().putString("user_id", loginResult.userid).commit()
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            finish()
                        }
                    }
                }
            }
        }

        register_button.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

    }


}
