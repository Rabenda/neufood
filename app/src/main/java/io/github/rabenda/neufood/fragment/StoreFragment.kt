package io.github.rabenda.neufood.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

import io.github.rabenda.neufood.R
import io.github.rabenda.neufood.adapters.StoreAdapter
import io.github.rabenda.neufood.model.retrofitModel
import kotlinx.android.synthetic.main.fragment_store.*
import io.github.rabenda.neufood.server.Server

/**
 * A simple [Fragment] subclass.
 *
 */
class StoreFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        storeRecyclerView.layoutManager = GridLayoutManager(context,1)
        showAllShops()
    }

    fun showAllShops() = doAsync {
        val storeList = Server.getAllShops()
        uiThread {
            storeRecyclerView.adapter = StoreAdapter(storeList)
        }
    }
}
