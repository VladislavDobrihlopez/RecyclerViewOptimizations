package com.example.fakevkhub.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fakevkhub.R
import com.example.fakevkhub.presentation.community_manager_screen.CommunityCreateManagerFragment
import com.example.fakevkhub.presentation.community_screen.CommunitiesFragment
import com.example.fakevkhub.presentation.community_screen.CommunitiesNavigationListener

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