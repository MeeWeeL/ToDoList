package com.meeweel.todolist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.meeweel.todolist.R
import com.meeweel.todolist.databinding.ActivityMainBinding
import com.meeweel.todolist.model.defaultQuest
import com.meeweel.todolist.view.deletedfragment.DeletedFragment
import com.meeweel.todolist.view.donefragment.DoneFragment
import com.meeweel.todolist.view.mainfragment.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        savedInstanceState?.let {} ?: refresh()
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
}