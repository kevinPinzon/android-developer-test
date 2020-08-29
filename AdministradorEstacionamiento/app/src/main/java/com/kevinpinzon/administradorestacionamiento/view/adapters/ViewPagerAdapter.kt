package com.kevinpinzon.administradorestacionamiento.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kevinpinzon.administradorestacionamiento.view.fragments.HomeFramgment
import com.kevinpinzon.administradorestacionamiento.view.fragments.RecordFragment

class ViewPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {

    companion object{
        private const val ARG_OBJECT = "object"
    }

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0    -> {
                RecordFragment()
            }
            1    -> {
                HomeFramgment()
            }
            else -> HomeFramgment()
        }
    }
}