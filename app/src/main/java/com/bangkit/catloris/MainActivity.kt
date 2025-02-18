package com.bangkit.catloris

import android.media.MediaPlayer
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bangkit.catloris.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(this, R.raw.catloris_backsound)
        mediaPlayer.isLooping = true
        mediaPlayer.start()

        supportActionBar?.hide()

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val navView = findViewById<BottomNavigationView>(R.id.nav_view)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_dashboard,
                R.id.navigation_article,
                R.id.navigation_scan,
                R.id.navigation_challenge,
                R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.navView.setupWithNavController(navController)

        binding.navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_dashboard -> {
                    navController.navigate(R.id.navigation_dashboard)
                    true
                }

                R.id.navigation_article -> {
                    navController.navigate(R.id.navigation_article)
                    true
                }

                R.id.navigation_scan -> {
                    navController.navigate(R.id.navigation_scan)
                    true
                }

                R.id.navigation_challenge -> {
                    navController.navigate(R.id.navigation_challenge)
                    true
                }

                R.id.navigation_profile -> {
                    navController.navigate(R.id.navigation_profile)
                    true
                }

                else -> false
            }
        }

        binding.fab.setOnClickListener {
            navController.navigate(R.id.navigation_scan)
        }

    }

    override fun onPause() {
        super.onPause()
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        }
    }

    override fun onResume() {
        super.onResume()
        if (!mediaPlayer.isPlaying) {
            mediaPlayer.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

}