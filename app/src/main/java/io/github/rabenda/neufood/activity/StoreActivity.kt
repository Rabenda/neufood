package io.github.rabenda.neufood.activity

import android.app.Activity
import android.os.Bundle
import io.github.rabenda.neufood.R

class StoreActivity : Activity() {

    private val storeId get() = intent.getStringExtra("shop_id")
    private val storeName get() = intent.getStringExtra("shopname")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)
    }


}
