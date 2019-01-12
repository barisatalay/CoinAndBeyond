package com.barisatalay.cointrackersample.ui.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.barisatalay.cointracker.CoinAndBeyond
import com.barisatalay.cointracker.data.Koineks
import com.barisatalay.cointracker.data.mdlCoinResponse
import javax.inject.Inject

class CoinListViewModel(private var count: Int = 0) : ViewModel(), LifecycleObserver {

    lateinit var projectListObservable: LiveData<mdlCoinResponse>
    private val koineks = CoinAndBeyond(Koineks())


    init {
        // If any transformation is needed, this can be simply done by Transformations class ...
        projectListObservable = koineks.getRepository().GetKoineks(arrayOf())
    }

    fun loadCurrencyList(): LiveData<mdlCoinResponse> {
        projectListObservable = koineks.getRepository().GetKoineks(arrayOf())
        return projectListObservable
    }

}