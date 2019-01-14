package com.barisatalay.cointrackersample.ui.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.LiveData
import com.barisatalay.cointracker.CoinAndBeyond
import com.barisatalay.cointracker.data.*


class CoinListViewModel : ViewModel(), IResponse{

    private lateinit var coinAndBeyond: CoinAndBeyond
    private val observableData = MutableLiveData<mdlCoinResponse>()

    private fun prepareLiveData() {
        when {
            coinAndBeyond.getDataset() is Koineks -> coinAndBeyond.getRepository().GetKoineks(arrayOf(), this)
            coinAndBeyond.getDataset() is Paribu -> coinAndBeyond.getRepository().GetParibu(arrayOf(), this)
            coinAndBeyond.getDataset() is BtcTurk -> coinAndBeyond.getRepository().GetBtcTurk(arrayOf(), this)
            coinAndBeyond.getDataset() is SistemKoin -> coinAndBeyond.getRepository().GetSistemKoin(arrayOf(), this)
        }
    }

    override fun onResponse(responseData: mdlCoinResponse) {
        observableData.postValue(responseData)
    }

    fun getObservableData(): LiveData<mdlCoinResponse> {
        return observableData
    }

    fun setObject(coinAndBeyond: CoinAndBeyond) {
        this.coinAndBeyond = coinAndBeyond

        prepareLiveData()
    }


}