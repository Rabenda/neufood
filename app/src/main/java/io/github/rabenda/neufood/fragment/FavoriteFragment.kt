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
import io.github.rabenda.neufood.adapters.CollectListAdapter
import io.github.rabenda.neufood.server.Server
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class FavoriteFragment : Fragment() {

    private val share get() = context!!.getSharedPreferences("NeuFood", Activity.MODE_PRIVATE)
    private val userId get() = share.getString("user_id", "")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favorite_RV.layoutManager = GridLayoutManager(context, 3)
        getCollectList("0")
        favorite_tabs.addOnTabSelectedListener(tabSelectedListener)
    }

    var tabSelectedListener = object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(p0: TabLayout.Tab?) {

        }

        override fun onTabUnselected(p0: TabLayout.Tab?) {

        }

        override fun onTabSelected(tab: TabLayout.Tab?) {
            when (tab?.text) {
                "店铺" -> getCollectList("0")
                else -> getCollectList("1")
            }
        }
    }

    fun getCollectList(flag: String) = doAsync {
        val list = Server.getAllUsrCollection(userId, flag)
        uiThread {
            favorite_RV.adapter = CollectListAdapter(list)
        }
    }

}
