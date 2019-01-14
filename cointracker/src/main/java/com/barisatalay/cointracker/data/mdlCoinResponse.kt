package com.barisatalay.cointracker.data

import com.barisatalay.cointracker.service.model.enmCoin
import com.barisatalay.cointracker.service.model.mdlCoin

class mdlCoinResponse {
    private var dataset = HashMap<enmCoin, ArrayList<mdlCoin>>()

    fun getCoinDetailFromType(type: enmCoin): ArrayList<mdlCoin>? {
        return dataset[type]
    }

    fun getCoinDetail(): HashMap<enmCoin, ArrayList<mdlCoin>> {
        return dataset
    }

    internal fun addCoinDetail(type: enmCoin, value: mdlCoin){
        if (dataset.containsKey(type)){
            dataset[type]?.add(value)
        }else{
            dataset[type] = arrayListOf(value)
        }
    }

    fun applyFilter(coinFilters: Array<enmCoin>){
        if (coinFilters.isEmpty()) return

        val wilBeDeleted= arrayListOf<enmCoin>()
        for (item in dataset)
            if (!coinFilters.contains(item.key))
                wilBeDeleted.add(item.key)

        for (item in wilBeDeleted)
            dataset.remove(item)
    }
}