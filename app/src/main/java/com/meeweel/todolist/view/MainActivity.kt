package com.meeweel.todolist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.meeweel.todolist.R
import com.meeweel.todolist.databinding.ActivityMainBinding
import com.meeweel.todolist.model.repository.defaultQuest
import com.meeweel.todolist.view.deletedfragment.DeletedFragment
import com.meeweel.todolist.view.donefragment.DoneFragment
import com.meeweel.todolist.view.mainfragment.MainFragment
import com.meeweel.todolist.viewmodel.MainViewModel
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        savedInstanceState?.let {} ?: refresh()
        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
        binding.navBar.background = null
        binding.fab.setOnClickListener {
            supportFragmentManager.apply {
                beginTransaction()
                    .replace(R.id.container, CreateFragment.newInstance(Bundle().apply {
                        putParcelable(CreateFragment.BUNDLE_EXTRA, defaultQuest)
                    }))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
        binding.navBar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.main_fragment_nav -> refresh(MainFragment())
                R.id.done_fragment_nav -> refresh(DoneFragment())
                R.id.not_done_fragment_nav -> refresh(DeletedFragment())
            }
            true
        }

    }
    private fun refresh(fragment: Fragment = MainFragment()) {
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, fragment)
            .commitNow()
    }

    override fun onDestroy() {
        viewModel.sync()
        thread {
            Thread.sleep(1000)
            runOnUiThread {
                super.onDestroy()
            }
        }
    }
    override fun onDetachedFromWindow() {
        viewModel.sync()
        super.onDetachedFromWindow()
    }
}