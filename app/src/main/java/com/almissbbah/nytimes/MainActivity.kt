package com.almissbbah.nytimes

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val navController = findNavController(R.id.nav_host_fragment)
        navController?.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.nav_splash
            ) {
                toolbar.visibility = View.GONE
            } else {
                toolbar.visibility = View.VISIBLE
            }
        }
    }

    fun updateActionBarTitle(title: String) {
        toolbar.title = title
    }
}