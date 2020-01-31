package com.example.photoapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.photoapplication.ui.usersFrag.UsersFragment


class MainActivity : AppCompatActivity() {

    private val manager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.fragment_holder, UsersFragment())
        transaction.commit()
    }

    override fun onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 2) {
            getFragmentManager().popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}
