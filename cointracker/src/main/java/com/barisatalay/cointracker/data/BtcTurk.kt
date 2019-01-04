package com.barisatalay.cointracker.data

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.barisatalay.cointracker.service.model.enmCoin
import com.barisatalay.cointracker.service.repository.ProjectRepository

class BtcTurk : IDataset {
    private lateinit var repository: ProjectRepository

    override fun getAvailableCoins(): Array<enmCoin> {
        return arrayOf(
            enmCoin.BTC,
            enmCoin.ETH,
            enmCoin.XRP,
            enmCoin.LTC,
            enmCoin.USDT,
            enmCoin.XLM
        )
    }

    override fun getBaseAddres(): String {
        return "https://www.btcturk.com"
    }

    override fun setRepository(repository: ProjectRepository) {
        this.repository = repository
    }

    override fun run(observer: Observer<mdlCoinResponse>, owner: LifecycleOwner) {
        repository.GetBtcTurk(getAvailableCoins()).observe(owner, observer)
    }
}