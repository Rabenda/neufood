package com.rabenda.constrainlayout

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.cancelButton
import org.jetbrains.anko.okButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login_bt_login.setOnClickListener {
            val userName = login_et_username.text.toString()
            val passWord = login_et_password.text.toString()
            this@MainActivity.alert("$userName\n$passWord\n","InfoShow"){
                cancelButton {}
                okButton {
                    login_et_username.setText("")
                    login_et_password.setText("")
                }
            }.show()
        }
    }
}
