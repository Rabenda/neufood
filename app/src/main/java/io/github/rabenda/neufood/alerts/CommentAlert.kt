package io.github.rabenda.neufood.alerts


import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.View
import io.github.rabenda.neufood.R
import io.github.rabenda.neufood.server.Server
import kotlinx.android.synthetic.main.comment_alert.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

// flag == false ----> add comment
class CommentAlert(context: Context, item_id: String, content: String = "", flag: Boolean = false) :
        AlertDialog.Builder(context) {

    init {
        if (flag) setTitle("修改评论") else setTitle("添加评论")
        val view = View.inflate(context, R.layout.comment_alert, null)
        view.et_content.text.append(content)
        setView(view)
        setPositiveButton("提交") { _, _ ->
            val newcontent = view.et_content.text.toString()
            if (newcontent != "")
                doAsync {
                    val re =
                            if (flag) Server.updateComment(item_id, newcontent) else Server.insertComment(item_id, newcontent)
                    uiThread {
                        if (re.success == "1")
                            context.toast("评论成功")
                        else
                            context.toast("提交失败")
                    }
                }
            else
                context.toast("内容不能为空")
        }
        setNegativeButton("取消", null)
    }
}
