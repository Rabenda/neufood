package io.github.rabenda.neufood.activity

import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import io.github.rabenda.neufood.R
import io.github.rabenda.neufood.fragment.StoreFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val storeFragment = StoreFragment()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.neufood_store -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.neufood_favorite -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.neufood_search -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.neufood_mine -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
