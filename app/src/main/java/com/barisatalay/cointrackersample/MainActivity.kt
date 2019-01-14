package com.barisatalay.cointrackersample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.barisatalay.cointracker.data.Koineks
import com.barisatalay.cointracker.data.Paribu
import com.barisatalay.cointrackersample.ui.adapter.BasePagerAdapter
import com.barisatalay.cointrackersample.ui.fragment.MarketFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var tabAdapter : BasePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabAdapter = BasePagerAdapter(supportFragmentManager)

        tabAdapter.addItem(MarketFragment.newInstance().setDataset(Paribu()))
        tabAdapter.addItem(MarketFragment.newInstance().setDataset(Koineks()))

        pager.adapter = tabAdapter

        tabs.setupWithViewPager(pager)
    }

}
