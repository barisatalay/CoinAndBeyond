package com.barisatalay.cointrackersample.ui.fragment

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.barisatalay.cointracker.CoinAndBeyond
import com.barisatalay.cointracker.data.IDataset
import com.barisatalay.cointracker.service.model.enmCoin
import com.barisatalay.cointracker.service.model.mdlCoin
import com.barisatalay.cointrackersample.R
import com.barisatalay.cointrackersample.core.model.Coin
import com.barisatalay.cointrackersample.ui.adapter.CoinAdapter
import com.barisatalay.cointrackersample.ui.viewmodel.CoinListViewModel
import kotlinx.android.synthetic.main.fragment_market.view.*


class MarketFragment: Fragment() {
    private lateinit var dataset: IDataset
    private val adapter = CoinAdapter(arrayListOf())
    private lateinit var viewModel: CoinListViewModel

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater, @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_market, container,false);

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        view.coinList.layoutManager = linearLayoutManager
        view.coinList.adapter = adapter
        view.coinList.setHasFixedSize(true)

        return view
    }

    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        showLoading()

        viewModel = ViewModelProviders.of(this).get(CoinListViewModel::class.java)

        viewModel.setObject(CoinAndBeyond(dataset))

        observeViewModel(viewModel)

        reloadData()
    }

    private fun reloadData() {
        Handler().postDelayed({
            showLoading()
            viewModel.refreshData()
        }, 10000)
    }

    private fun observeViewModel(viewModel: CoinListViewModel) {
        viewModel.getObservableData().observe(this, Observer {
            Log.i("FRAGMENT", "${getTitle()} Data geldi")
            hideLoading()
            adapter.setAll(prepareCoinToList(it.getCoinDetail()))
            reloadData()
        })
    }

    fun setDataset(dataset: IDataset): MarketFragment{
        this.dataset = dataset

        return this
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

    fun getTitle():String{
        return dataset.javaClass.simpleName
    }

    fun hideLoading(){
        view?.progres_layout?.visibility = View.GONE
    }

    fun showLoading(){
        view?.progres_layout?.visibility = View.VISIBLE
    }
}