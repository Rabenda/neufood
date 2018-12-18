package io.github.rabenda.neufood.alerts

import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.View
import io.github.rabenda.neufood.R
import io.github.rabenda.neufood.bean.UsrBean
import io.github.rabenda.neufood.server.Server
import kotlinx.android.synthetic.main.activity_register.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class ResetUsrInfoAlert(context: Context, var usr: UsrBean, var passwd: String) : AlertDialog.Builder(context) {
    init {
        setTitle("修改用户信息")
        val view = View.inflate(context, R.layout.activity_register, null)
        with(view) {
            register_username.setText(usr.username)
            register_password.setText(passwd)
            register_address.setText(usr.address)
            register_mobilenum.setText(usr.mobilenum)
            register_button.visibility = View.INVISIBLE
        }
        setView(view)
        setPositiveButton("提交修改") { _, _ ->
            usr.username = view.register_username.text.toString()
            usr.address = view.register_address.text.toString()
            usr.mobilenum = view.register_mobilenum.text.toString()
            usr.userpass = view.register_password.text.toString()
            doAsync {
                var re = Server.updateUserById(usr.user_id, usr.username, usr.userpass, usr.mobilenum, usr.address)
                uiThread {
                    if (re.success == "1")
                        context.toast("修改成功")
                    else
                        context.toast("修改失败")
                }
            }
        }
        setNegativeButton("取消", null)
    }
}