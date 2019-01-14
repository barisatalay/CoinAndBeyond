package com.barisatalay.cointrackersample.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import com.barisatalay.cointrackersample.R
import com.barisatalay.cointrackersample.core.AdapterDiffUtil
import com.barisatalay.cointrackersample.core.model.Coin
import com.barisatalay.cointrackersample.ui.holder.CoinHolder


class CoinAdapter(private val coinHeaders: ArrayList<Coin>): RecyclerView.Adapter<CoinHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coin_header, parent,false)
        return CoinHolder(view)
    }

    override fun getItemCount(): Int {
        return coinHeaders.size
    }

    override fun onBindViewHolder(holder: CoinHolder, position: Int) {
        holder.bind(coinHeaders[position])
    }

    fun setAll(list: ArrayList<Coin>) {
        val noteDiffUtil = AdapterDiffUtil(coinHeaders, list)
        val diffResult = DiffUtil.calculateDiff(noteDiffUtil)
        coinHeaders.clear()
        coinHeaders.addAll(list)
//        notifyDataSetChanged()
        diffResult.dispatchUpdatesTo(this)
    }
}