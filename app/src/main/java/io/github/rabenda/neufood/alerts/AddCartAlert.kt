package io.github.rabenda.neufood.alerts

import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.View
import io.github.rabenda.neufood.R
import io.github.rabenda.neufood.server.Server
import kotlinx.android.synthetic.main.addcart_alert.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class AddCartAlert(context: Context, usr_id: String, food_id: String) : AlertDialog.Builder(context) {

    init {
        setTitle("加入购物车")
        val view = View.inflate(context, R.layout.addcart_alert, null)
        var num = 1
        with(view) {
            tv_num.text = "$num"
            rb_decnum.setOnClickListener {
                if (num > 1) num--
                tv_num.text = "$num"
            }
            rb_addnum.setOnClickListener {
                num++
                tv_num.text = "$num"
            }
        }
        setView(view)
        setNegativeButton("取消", null)
        setPositiveButton("加入购物车") { _, _ ->
            doAsync {
                val re = Server.addCart(usr_id, food_id, num)
                uiThread {
                    if (re.success == "1")
                        context.toast("成功加入购物车")
                    else
                        context.toast("加入购物车失败")
                }
            }
        }
    }
}