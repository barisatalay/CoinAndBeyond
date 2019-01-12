package com.barisatalay.cointracker.data

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.barisatalay.cointracker.service.model.enmCoin
import com.barisatalay.cointracker.service.repository.ProjectRepository

class SistemKoin : IDataset {
    private lateinit var repository: ProjectRepository

    override fun run(observer: Observer<mdlCoinResponse>, owner: LifecycleOwner) {
        repository.GetSistemKoin(getAvailableCoins()).observe(owner, observer)
    }

    override fun setRepository(repository: ProjectRepository) {
        this.repository = repository
    }

    override fun getBaseAddres(): String {
        return "https://sistemkoin.com"
    }

    override fun getAvailableCoins(): Array<enmCoin> {
        return arrayOf(
            enmCoin.BTC,
            enmCoin.ETH,
            enmCoin.XRP,
            enmCoin.LTC,
            enmCoin.DOGE,
            enmCoin.BTG,
            enmCoin.ZEC,
            enmCoin.DGB,
            enmCoin.XLM,
            enmCoin.TRX,
            enmCoin.BEN,
            enmCoin.XIN,
            enmCoin.WRC,
            enmCoin.USDT,
            enmCoin.SYS,
            enmCoin.WPR,
            enmCoin.XSN,
            enmCoin.BTW,
            enmCoin.DRG,
            enmCoin.LNC,
            enmCoin.MTC,
            enmCoin.MEDIC,
            enmCoin.BTCP,
            enmCoin.RLX,
            enmCoin.HBZ,
            enmCoin.PIRL
        )
    }

    override fun getRepository(): ProjectRepository {
        return repository
    }
}