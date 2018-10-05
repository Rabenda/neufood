package io.github.rabenda.neufood.activity

import android.support.v7.app.AppCompatActivity
import android.widget.Toast

open class BaseActivity: AppCompatActivity() {
    fun showToast(content: String) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
    }
}