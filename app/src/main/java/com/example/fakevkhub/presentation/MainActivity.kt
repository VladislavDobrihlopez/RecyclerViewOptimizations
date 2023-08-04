package com.example.fakevkhub.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fakevkhub.R

class MainActivity : AppCompatActivity(), CommunitiesNavigationListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initCommunityScreen()
    }

    private fun initCommunityScreen() {
        val instance = CommunitiesFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, instance)
            .commit()
    }

    override fun navigateToCommunityCreatorScreen() {
        supportFragmentManager.beginTransaction()
            .addToBackStack(CommunityCreateManagerFragment.FRAGMENT_NAME)
            .replace(R.id.fragmentContainer, CommunityCreateManagerFragment.newInstance())
            .commit()
    }
}