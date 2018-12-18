package io.github.rabenda.neufood.fragment


import android.app.Activity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import io.github.rabenda.neufood.R
import io.github.rabenda.neufood.adapters.CommentListAdaper
import io.github.rabenda.neufood.alerts.ResetUsrInfoAlert
import io.github.rabenda.neufood.bean.UsrBean
import io.github.rabenda.neufood.server.Server
import kotlinx.android.synthetic.main.fragment_mine.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * A simple [Fragment] subclass.
 *
 */
class MineFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mine, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usrInfo()
        usrinfobar.setOnClickListener {
            ResetUsrInfoAlert(context!!, usr, passwd).create().show()
            usrInfo()
        }
        rv_usroc.layoutManager = GridLayoutManager(context, 1)
        doAsync {
            val list = Server.getAllOrdersByUser(usr_id)
            uiThread {
                rv_usroc.adapter = CommentListAdaper(usr_id, list, 1)
            }
        }
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(p0: TabLayout.Tab?) {
                rv_usroc.adapter = when (p0?.text) {
                    "订单" -> CommentListAdaper(usr_id, listOf(), 1)
                    else -> CommentListAdaper(usr_id, listOf(), 2)
                }
            }

            override fun onTabReselected(p0: TabLayout.Tab?) {}
            override fun onTabUnselected(p0: TabLayout.Tab?) {}
        })
    }

    private fun usrInfo() = doAsync {
        usr = Server.getUserById(usr_id)
        uiThread {
            tv_usrname.text = usr.username
            tv_phone.text = usr.mobilenum
            tv_addr.text = usr.address
            tv_com.text = usr.comment
            shareEditor?.putString("usrname", usr.username)
                    ?.commit()
        }
    }

    private val share get() = activity?.getSharedPreferences("NeuFood", Activity.MODE_PRIVATE)
    private val shareEditor get() = share?.edit()
    private val usr_id get() = share?.getString("user_id", "") ?: ""
    private val passwd get() = share?.getString("passwd", "") ?: ""
    private lateinit var usr: UsrBean

}
