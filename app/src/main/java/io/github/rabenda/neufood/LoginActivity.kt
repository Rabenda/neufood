package io.github.rabenda.neufood

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.beardedhen.androidbootstrap.TypefaceProvider
import kotlinx.android.synthetic.main.activity_login.*

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {

    val TAG = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TypefaceProvider.registerDefaultIconSets()
        setContentView(R.layout.activity_login)

        login_button.setOnClickListener {
            Log.i(TAG, login_username.text.toString())
            Log.i(TAG, login_password.text.toString())
        }

    }


}
