package com.barisatalay.cointracker.data

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.barisatalay.cointracker.service.model.enmCoin
import com.barisatalay.cointracker.service.repository.ProjectRepository

class Paribu : IDataset {
    private lateinit var repository: ProjectRepository

    override fun run(observer: Observer<mdlCoinResponse>, owner: LifecycleOwner) {
        repository.GetParibu(getAvailableCoins()).observe(owner, observer)
    }

    override fun setRepository(repository: ProjectRepository) {
        this.repository = repository
    }

    override fun getBaseAddres(): String {
        return "https://www.paribu.com"
    }

    override fun getAvailableCoins(): Array<enmCoin> {
        return arrayOf(enmCoin.BTC, enmCoin.XRP, enmCoin.ETH, enmCoin.XLM, enmCoin.ADA, enmCoin.LTC, enmCoin.EOS)
    }
}