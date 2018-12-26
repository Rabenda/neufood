package com.rabenda.fourfragment

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        main_view_pager.adapter = viewPagerAdapter
        main_view_pager.addOnPageChangeListener(viewPagerOnPageChangeListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.one -> {
                main_view_pager.currentItem = 0
            }
            R.id.two -> {
                main_view_pager.currentItem = 1
            }
            R.id.three -> {
                main_view_pager.currentItem = 2
            }
            R.id.four -> {
                main_view_pager.currentItem = 3
            }
        }
        true
    }

    private val fragmentList = listOf(OneFragment(), TwoFragment(), ThreeFragment(), FourFragment())

    private val viewPagerAdapter get() = object : FragmentPagerAdapter(supportFragmentManager) {
        override fun getItem(p0: Int): Fragment = fragmentList[p0]
        override fun getCount(): Int = fragmentList.size
    }

    private val viewPagerOnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(p0: Int) {}

        override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}

        override fun onPageSelected(p0: Int) {
            when(p0) {
                0 -> main_navigation.selectedItemId = R.id.one
                1 -> main_navigation.selectedItemId = R.id.two
                2 -> main_navigation.selectedItemId = R.id.three
                3 -> main_navigation.selectedItemId = R.id.four
            }
        }
    }
}
