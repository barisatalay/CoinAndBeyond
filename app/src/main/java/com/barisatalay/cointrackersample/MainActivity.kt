package com.barisatalay.cointrackersample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.barisatalay.cointracker.data.BtcTurk
import com.barisatalay.cointracker.data.Koineks
import com.barisatalay.cointracker.data.Paribu
import com.barisatalay.cointracker.data.SistemKoin
import com.barisatalay.cointrackersample.ui.adapter.BasePagerAdapter
import com.barisatalay.cointrackersample.ui.fragment.MarketFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var tabAdapter : BasePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabAdapter = BasePagerAdapter(supportFragmentManager)

        tabAdapter.addItem(MarketFragment().setDataset(Paribu()))
        tabAdapter.addItem(MarketFragment().setDataset(Koineks()))
        tabAdapter.addItem(MarketFragment().setDataset(BtcTurk()))
        tabAdapter.addItem(MarketFragment().setDataset(SistemKoin()))

        pager.offscreenPageLimit = tabAdapter.count - 1
        pager.adapter = tabAdapter

        tabs.setupWithViewPager(pager)
    }

}
