package com.rabenda.recyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_rv.layoutManager = GridLayoutManager(this, 2)
        main_rv.adapter = Adapter(dataList)
    }

    private val dataList = List<String>(50){it ->
         it.toString()
    }
}
