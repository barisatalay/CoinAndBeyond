package com.barisatalay.cointrackersample.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.barisatalay.cointrackersample.ui.fragment.MarketFragment

class BasePagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {
    private val list : ArrayList<Fragment> = arrayListOf()

    override fun getItem(position: Int): Fragment {
        return list[position]
    }

    override fun getCount(): Int {
        return list.size
    }

    fun addItem(fragment: Fragment): BasePagerAdapter{
        list.add(fragment)
        return this
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return (list[position] as MarketFragment).getTitle()
    }
}