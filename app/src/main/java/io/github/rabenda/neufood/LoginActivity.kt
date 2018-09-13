package io.github.rabenda.neufood

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.beardedhen.androidbootstrap.TypefaceProvider


/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TypefaceProvider.registerDefaultIconSets()
        setContentView(R.layout.activity_login)
    }
}
