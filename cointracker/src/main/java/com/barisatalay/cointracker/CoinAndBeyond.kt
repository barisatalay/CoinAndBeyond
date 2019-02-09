package com.barisatalay.cointracker

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.barisatalay.cointracker.data.IDataset
import com.barisatalay.cointracker.data.mdlCoinResponse
import com.barisatalay.cointracker.helper.UtilsNetwork
import com.barisatalay.cointracker.service.model.enmCoin
import com.barisatalay.cointracker.service.repository.ProjectRepository

class CoinAndBeyond(prmDataset: IDataset) {
    private var coinFilters: Array<enmCoin> = arrayOf()
    private var dataset: IDataset
    private lateinit var owner: LifecycleOwner

    private var repository: ProjectRepository = UtilsNetwork.provideProjectRepository()

    init {
        this.dataset = prmDataset
        repository.setHost(dataset.getBaseAddres())
        dataset.setRepository(repository)
    }

    constructor(prmDataset: IDataset, prmOwner: LifecycleOwner) : this(prmDataset) {
        this.dataset = prmDataset
        this.owner = prmOwner
    }
    /**
     * TODO  TR: Daha sonra doldurulacak
     *
     * TODO  ENG: It will be fill another time
     * */
    fun getCoins(observer: Observer<mdlCoinResponse>): CoinAndBeyond{
        repository.coinFilters(coinFilters)
        dataset.run(observer, owner)

        return this
    }


    fun getRepository(): ProjectRepository{
        return dataset.getRepository()
    }
    /**
     * TR: Activity vb. kapatılırken bu çalıştırılması tavsiye edilir. Websitelere gönderilen istekleri iptal eder.
     *
     * ENG: It is recommended that you use this method when you close activity etc. It will cancelled web site requests
     * */
    fun cancelAllRequests(){
        repository.cancelAll()
    }
    /**
     * TR: Kullanmaya çalıştığınız datasetin(Web Sitenin) yayınladığı coinleri döndürür
     *
     * EN: Returns the coins published by the dataset(the Web Site) you are trying to use
     * */
    fun getAvailableCoins():Array<enmCoin>{
        return dataset.getAvailableCoins()
    }
    /**
     * TR: "getCoins" metodundan dönecek olan coin listesi, burada verdiğiniz listeye göre filtrelenir.
     *     NOT: boş liste gönderirseniz hepsi listelenir.
     *
     * EN: The coin list that will be returned from the "getCoins" method is filtered according to the list you provided here.
     *     NOTE: If you submit a blank list, they are all listed.
     * */
    fun setCoinFilter(coins: Array<enmCoin>): CoinAndBeyond {
        this.coinFilters = coins

        return this
    }

    fun getDataset(): IDataset{
        return dataset
    }
}