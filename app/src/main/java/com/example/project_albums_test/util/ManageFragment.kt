package com.example.project_albums_test.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object ManageFragment {

    fun addFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        content: Int, isBackStack: Boolean = true
    ) {
        val ft = fragmentManager.beginTransaction().replace(content, fragment)
        if (isBackStack) ft.addToBackStack(null)
        ft.commit()
    }
}