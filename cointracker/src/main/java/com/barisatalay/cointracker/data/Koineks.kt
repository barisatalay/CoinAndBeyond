package com.barisatalay.cointracker.data

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.barisatalay.cointracker.service.model.enmCoin
import com.barisatalay.cointracker.service.repository.ProjectRepository

class Koineks : IDataset {
    private lateinit var repository: ProjectRepository

    override fun run(observer: Observer<mdlCoinResponse>, owner: LifecycleOwner) {
        repository.GetKoineks(getAvailableCoins()).observe(owner, observer)
    }

    override fun setRepository(repository: ProjectRepository) {
        this.repository = repository
    }

    override fun getBaseAddres(): String {
        return "https://koineks.com"
    }

    override fun getAvailableCoins(): Array<enmCoin> {
        return arrayOf(
            enmCoin.BTC,
            enmCoin.ETH,
            enmCoin.TRX,
            enmCoin.USDT,
            enmCoin.BCH,
            enmCoin.LTC,
            enmCoin.DOGE,
            enmCoin.DASH,
            enmCoin.XRP,
            enmCoin.XLM,
            enmCoin.XEM,
            enmCoin.BTG,
            enmCoin.ETC
        )
    }
}