package com.example.project_albums_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.project_albums_test.fragment.AlbumFragment
import com.example.project_albums_test.util.ManageFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ManageFragment.addFragment(
            supportFragmentManager,
            AlbumFragment(),
            R.id.layout_content,
            false
        )


    }
}