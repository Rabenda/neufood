package io.github.rabenda.neufood.activity

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import io.github.rabenda.neufood.R
import io.github.rabenda.neufood.fragment.FavoriteFragment
import io.github.rabenda.neufood.fragment.MineFragment
import io.github.rabenda.neufood.fragment.SearchFragment
import io.github.rabenda.neufood.fragment.StoreFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {
    private val storeFragment = StoreFragment()
    private val searchFragment = SearchFragment()
    private val favoriteFragment = FavoriteFragment()
    private val mineFragment = MineFragment()

    private val fragmentList = listOf(storeFragment, searchFragment, favoriteFragment, mineFragment)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        main_view_pager.adapter = viewPagerAdapter
        main_view_pager.addOnPageChangeListener(viewPagerListener)
        fabt_shopcar.setOnClickListener { startActivity<ShopCarActivity>() }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.neufood_store -> {
                main_view_pager.currentItem = 0
            }
            R.id.neufood_search -> {
                main_view_pager.currentItem = 1
            }
            R.id.neufood_favorite -> {
                main_view_pager.currentItem = 2
            }
            R.id.neufood_mine -> {
                main_view_pager.currentItem = 3
            }
        }
        true
    }



    private val viewPagerAdapter = object : FragmentPagerAdapter(supportFragmentManager){
        override fun getCount(): Int = fragmentList.size
        override fun getItem(p0: Int): Fragment = fragmentList[p0]
    }

    private val viewPagerListener = object : ViewPager.OnPageChangeListener {
        override fun onPageSelected(index: Int) {
            when(index) {
                0 -> bottom_navigation.selectedItemId = R.id.neufood_store
                1 -> bottom_navigation.selectedItemId = R.id.neufood_search
                2 -> bottom_navigation.selectedItemId = R.id.neufood_favorite
                3 -> bottom_navigation.selectedItemId = R.id.neufood_mine
            }
        }

        override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}
        override fun onPageScrollStateChanged(p0: Int) {}
    }


}
