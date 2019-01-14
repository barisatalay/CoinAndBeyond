package com.barisatalay.cointrackersample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.barisatalay.cointracker.CoinAndBeyond
import com.barisatalay.cointracker.data.Koineks
import com.barisatalay.cointracker.data.Paribu
import com.barisatalay.cointracker.service.model.enmCoin
import com.barisatalay.cointracker.service.model.mdlCoin
import com.barisatalay.cointrackersample.core.model.Coin
import com.barisatalay.cointrackersample.ui.adapter.CoinAdapter
import com.barisatalay.cointrackersample.ui.viewmodel.CoinListViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val adapter = CoinAdapter(arrayListOf())

    private val paribu = CoinAndBeyond(Paribu())
    private var paribuCoinDetail : HashMap<enmCoin, ArrayList<mdlCoin>> = hashMapOf()

    private val koineks = CoinAndBeyond(Koineks())
    private var koineksCoinDetail : HashMap<enmCoin, ArrayList<mdlCoin>> = hashMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hideLoading()

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        coinList.layoutManager = linearLayoutManager
        coinList.adapter = adapter
        coinList.setHasFixedSize(true)

        val currencyViewModel = ViewModelProviders.of(this).get(CoinListViewModel::class.java)
        currencyViewModel.let { lifecycle.addObserver(it) }

        currencyViewModel.loadCurrencyList().observe(this, Observer { currencyList ->
            print(2)
        })

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                showLoading()
                when (p0?.position){
                    0-> {
                        adapter.setAll(prepareCoinToList(paribuCoinDetail))
                    }
                    1-> {
                        adapter.setAll(prepareCoinToList(koineksCoinDetail))
                    }

                }
                hideLoading()
            }
        })
    }


    private fun showLoading() {
        progres_layout.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        progres_layout.visibility = View.GONE
    }

    private fun prepareCoinToList(source: HashMap<enmCoin, ArrayList<mdlCoin>>): ArrayList<Coin> {
        val list = ArrayList<Coin>()

        for(item in source){
            if (!item.value.isEmpty()) {
                list.add(Coin(0, item.key, item.value[0]))
            }
        }
        return list
    }
}
