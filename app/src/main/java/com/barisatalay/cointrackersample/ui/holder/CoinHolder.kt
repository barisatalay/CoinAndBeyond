package com.barisatalay.bitcoinveotesi.ui.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.barisatalay.bitcoinveotesi.core.model.Coin
import kotlinx.android.synthetic.main.item_coin_header.view.*

class CoinHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(coin: Coin) {
        itemView.type.text = coin.type.toString()
        itemView.last.text = "${coin.bestCoin.last} ${coin.bestCoin.currency.name}"
        itemView.high.text = coin.bestCoin.high.toString()
        itemView.low.text = coin.bestCoin.low.toString()
        itemView.volume.text = coin.bestCoin.volume.toString()
    }
}