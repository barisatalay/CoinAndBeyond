package com.barisatalay.cointracker.data

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.barisatalay.cointracker.service.model.enmCoin
import com.barisatalay.cointracker.service.repository.ProjectRepository

interface IDataset {
    fun getAvailableCoins(): Array<enmCoin>
    fun getBaseAddres(): String
    fun setRepository(repository: ProjectRepository)
    fun run(observer: Observer<mdlCoinResponse>, owner: LifecycleOwner)
    fun getRepository():ProjectRepository
}