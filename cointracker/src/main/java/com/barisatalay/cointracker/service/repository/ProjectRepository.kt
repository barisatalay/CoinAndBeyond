package com.barisatalay.cointracker.service.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.barisatalay.cointracker.data.IResponse
import com.barisatalay.cointracker.data.mdlCoinResponse
import com.barisatalay.cointracker.helper.BaseObserver
import com.barisatalay.cointracker.helper.UrlProviderInterceptor
import com.barisatalay.cointracker.helper.UtilsNetwork
import com.barisatalay.cointracker.service.model.*
import com.google.gson.Gson
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import okhttp3.Dispatcher
import org.json.JSONObject

class ProjectRepository(
    private val restfulRequest: RestfulRequest,
    private val scheduler: Scheduler,
    private val provideDispatcher: Dispatcher,
    private val provideUrlProvider: UrlProviderInterceptor
) {
    private var lastHost: String = ""
    private var compositeDisposable = CompositeDisposable()
    private var coinFilters: Array<enmCoin> = arrayOf()
    private val gson: Gson = UtilsNetwork.provideGson()

    fun GetParibu(coinTypes: Array<enmCoin>, listener: IResponse?){
        compositeDisposable.add(restfulRequest.GetParibu()
            .subscribeOn(scheduler)
            .observeOn(scheduler)
            .subscribeWith(object : BaseObserver<String>(){
                override fun onResponse(response: String) {
                    val result = mdlCoinResponse()
                    val parsingModel = gson.fromJson(response, mdlParibu::class.java)

                    if (coinTypes.isEmpty() || coinTypes.contains(enmCoin.BTC)){
                        parsingModel.getBTCTL()?.let {
                            result.addCoinDetail(enmCoin.BTC, convertParibuToCoin(it, enmCoin.BTC.name))
                        }
                    }
                    if (coinTypes.isEmpty() || coinTypes.contains(enmCoin.XRP)){
                        parsingModel.getXRPTL()?.let {
                            result.addCoinDetail(enmCoin.XRP,convertParibuToCoin(it, enmCoin.XRP.name))
                        }
                    }
                    if (coinTypes.isEmpty() || coinTypes.contains(enmCoin.ETH)) {
                        parsingModel.getETHTL()?.let {
                            result.addCoinDetail(enmCoin.ETH, convertParibuToCoin(it, enmCoin.ETH.name))
                        }
                    }
                    if (coinTypes.isEmpty() || coinTypes.contains(enmCoin.XLM)) {
                        parsingModel.getXLMTL()?.let {
                            result.addCoinDetail(enmCoin.XLM, convertParibuToCoin(it, enmCoin.XLM.name))
                        }
                    }
                    if (coinTypes.isEmpty() || coinTypes.contains(enmCoin.ADA)) {
                        parsingModel.getADATL()?.let {
                            result.addCoinDetail(enmCoin.ADA, convertParibuToCoin(it, enmCoin.ADA.name))
                        }
                    }
                    if (coinTypes.isEmpty() || coinTypes.contains(enmCoin.LTC)) {
                        parsingModel.getLTCTL()?.let {
                            result.addCoinDetail(enmCoin.LTC, convertParibuToCoin(it, enmCoin.LTC.name))
                        }
                    }
                    if (coinTypes.isEmpty() || coinTypes.contains(enmCoin.EOS)) {
                        parsingModel.getEOSTL()?.let {
                            result.addCoinDetail(enmCoin.EOS, convertParibuToCoin(it, enmCoin.EOS.name))
                        }
                    }

                    result.applyFilter(coinFilters)

                    listener?.let {
                        it.onResponse(result)
                    }

                }

                override fun onResponseError(e: Throwable) {
                    pushErrorMessageToLog("Paribu", e)
                }

            }))
    }

    internal fun GetParibu(coinTypes: Array<enmCoin>): LiveData<mdlCoinResponse>{
        val data = MutableLiveData<mdlCoinResponse>()
        GetParibu(coinTypes, object : IResponse{
            override fun onResponse(responseData: mdlCoinResponse) {
                data.postValue(responseData)
            }
        })
        return data
    }

    fun GetKoineks(coinTypes: Array<enmCoin>, listener: IResponse?): MutableLiveData<mdlCoinResponse> {

        val data = MutableLiveData<mdlCoinResponse>()

        compositeDisposable.add(restfulRequest.GetKoineks()
            .subscribeOn(scheduler)
            .observeOn(scheduler)
            .subscribeWith(object : BaseObserver<String>(){
                override fun onResponse(response: String) {
                    val result = mdlCoinResponse()
                    val parsingModel = gson.fromJson(response, mdlKoineks::class.java)

                    if (coinTypes.isEmpty() || coinTypes.contains(enmCoin.BTC)) {
                        parsingModel.BTC?.let {
                            //                            result.add(convertKoineksToCoin(it)!!)
                            result.addCoinDetail(enmCoin.BTC, convertKoineksToCoin(it))
                        }
                    }
                    if (coinTypes.isEmpty() || coinTypes.contains(enmCoin.ETH)) {
                        parsingModel.ETH?.let {
                            result.addCoinDetail(enmCoin.ETH, convertKoineksToCoin(it))
                        }
                    }
                    if (coinTypes.isEmpty() || coinTypes.contains(enmCoin.TRX)) {
                        parsingModel.TRX?.let {
                            result.addCoinDetail(enmCoin.TRX, convertKoineksToCoin(it))
                        }
                    }
                    if (coinTypes.isEmpty() || coinTypes.contains(enmCoin.USDT)) {
                        parsingModel.USDT?.let {
                            result.addCoinDetail(enmCoin.USDT, convertKoineksToCoin(it))
                        }
                    }
                    if (coinTypes.isEmpty() || coinTypes.contains(enmCoin.BCH)) {
                        parsingModel.BCH?.let {
                            result.addCoinDetail(enmCoin.BCH, convertKoineksToCoin(it))
                        }
                    }
                    if (coinTypes.isEmpty() || coinTypes.contains(enmCoin.LTC)) {
                        parsingModel.LTC?.let {
                            result.addCoinDetail(enmCoin.LTC, convertKoineksToCoin(it))
                        }
                    }
                    if (coinTypes.isEmpty() || coinTypes.contains(enmCoin.DOGE)) {
                        parsingModel.DOGE?.let {
                            result.addCoinDetail(enmCoin.DOGE, convertKoineksToCoin(it))
                        }
                    }
                    if (coinTypes.isEmpty() || coinTypes.contains(enmCoin.DASH)) {
                        parsingModel.DASH?.let {
                            result.addCoinDetail(enmCoin.DASH, convertKoineksToCoin(it))
                        }
                    }
                    if (coinTypes.isEmpty() || coinTypes.contains(enmCoin.XRP)) {
                        parsingModel.XRP?.let {
                            result.addCoinDetail(enmCoin.XRP, convertKoineksToCoin(it))
                        }
                    }
                    if (coinTypes.isEmpty() || coinTypes.contains(enmCoin.XLM)) {
                        parsingModel.XLM?.let {
                            result.addCoinDetail(enmCoin.XLM, convertKoineksToCoin(it))
                        }
                    }
                    if (coinTypes.isEmpty() || coinTypes.contains(enmCoin.XEM)) {
                        parsingModel.XEM?.let {
                            result.addCoinDetail(enmCoin.XEM, convertKoineksToCoin(it))
                        }
                    }
                    if (coinTypes.isEmpty() || coinTypes.contains(enmCoin.BTG)) {
                        parsingModel.BTG?.let {
                            result.addCoinDetail(enmCoin.BTG, convertKoineksToCoin(it))
                        }
                    }
                    if (coinTypes.isEmpty() || coinTypes.contains(enmCoin.ETC)) {
                        parsingModel.ETC?.let {
                            result.addCoinDetail(enmCoin.ETC, convertKoineksToCoin(it))
                        }
                    }

                    result.applyFilter(coinFilters)


                    data.postValue(result)
//                    data.postValue(result)
                }

                override fun onResponseError(e: Throwable) {
                    pushErrorMessageToLog("Koineks", e)
                }

            }))
        return data

    }

    fun GetKoineks(coinTypes: Array<enmCoin>): LiveData<mdlCoinResponse>{
        val data = MutableLiveData<mdlCoinResponse>()

        GetKoineks(coinTypes, object : IResponse{
            override fun onResponse(responseData: mdlCoinResponse) {
                data.postValue(responseData)
            }
        })

        return data
    }

    fun GetBtcTurk(coinTypes: Array<enmCoin>, listener: IResponse?){
        compositeDisposable.add(restfulRequest.GetBtcTurk()
            .subscribeOn(scheduler)
            .observeOn(scheduler)
            .subscribeWith(object : BaseObserver<String>(){
                override fun onResponse(response: String) {
                    val result = mdlCoinResponse()
                    val parsingModel = gson.fromJson(response, Array<mdlCoinBtcTurk>::class.java).asList()

                    for (item in parsingModel) {
                        val coin = enmCoin.valueOf(item.numeratorsymbol)
                        if (coinTypes.isEmpty() || coinTypes.contains(coin)){
                            result.addCoinDetail(coin, convertBtcTurkToCoin(item))
                        }
                    }
                    result.applyFilter(coinFilters)
                    listener?.onResponse(result)
                }

                override fun onResponseError(e: Throwable) {
                    pushErrorMessageToLog("BtcTurk", e)
                }

            }))
    }

    internal fun GetBtcTurk(coinTypes: Array<enmCoin>): LiveData<mdlCoinResponse> {
        val data = MutableLiveData<mdlCoinResponse>()

        GetBtcTurk(coinTypes, object : IResponse{
            override fun onResponse(responseData: mdlCoinResponse) {
                data.postValue(responseData)
            }
        })
        return data
    }

    fun GetSistemKoin(coinTypes: Array<enmCoin>, listener: IResponse?){
        compositeDisposable.add(restfulRequest.GetSistemKoin()
            .subscribeOn(scheduler)
            .observeOn(scheduler)
            .subscribeWith(object : BaseObserver<String>(){
                override fun onResponse(response: String) {
                    val result = mdlCoinResponse()
                    val sistemData: JSONObject
                    try {
                        sistemData = JSONObject(response).getJSONObject("data")
                    }catch (e : Exception){
                        listener?.onResponse(result)
                        pushErrorMessageToLog("SistemKoin", e)
                        return
                    }

                    sistemData.keys().forEach { key ->
                        val type = enumValues<enmCoin>().find {
                            it.name == key
                        }

                        type?.let {
                            if (coinTypes.isEmpty() || coinTypes.contains(it)) {
                                val detailList = sistemData.getJSONArray(key)
                                for (i in 0..(detailList.length() - 1)) {
                                    val rowItem = detailList.getJSONObject(i)
                                    convertSistemKoinToCoin(rowItem)?.let {coin->
                                        result.addCoinDetail(it, coin)
                                    }
                                }
                            }
                        }
                    }

                    result.applyFilter(coinFilters)
                    listener?.onResponse(result)
                }

                override fun onResponseError(e: Throwable) {
                    pushErrorMessageToLog("SistemKoin", e)
                }

            }))
    }

    internal fun GetSistemKoin(coinTypes: Array<enmCoin>): LiveData<mdlCoinResponse>  {
        val data = MutableLiveData<mdlCoinResponse>()

        GetSistemKoin(coinTypes, object : IResponse{
            override fun onResponse(responseData: mdlCoinResponse) {
                data.postValue(responseData)
            }

        })
        return data
    }

    private fun pushErrorMessageToLog(tag: String, e: Throwable) {
        e.message?.let {
            Log.e("CoinAndBeyond", "$tag Response Error: $it")
        }
    }

    private fun convertParibuToCoin(response: mdlCoinParibu, name: String): mdlCoin {
        val coin:mdlCoin
        val last = response.last
        val lowest = response.low24hr
        val highest = response.high24hr
        val volume = response.volume
        val currency = enmCoin.TRY

        coin = mdlCoin(currency, last!!, lowest!!, highest!!, volume!!, enmCoin.valueOf(name).toString())

        return coin
    }

    private fun convertBtcTurkToCoin(response: mdlCoinBtcTurk): mdlCoin {
        val coin:mdlCoin
        val last = response.last
        val lowest = response.low
        val highest = response.high
        val volume = response.volume
        val finded = enumValues<enmCoin>().find {
            it.name == response.numeratorsymbol
        }
        val name = finded?.toString() ?: "${response.numeratorsymbol} / ${response.denominatorsymbol}"

        val currency = enmCoin.valueOf(response.denominatorsymbol)

        coin = mdlCoin(currency, last!!.toDouble(), lowest!!.toDouble(), highest!!.toDouble(), volume!!.toDouble(), name)

        return coin
    }

    private fun convertKoineksToCoin(response: mdlCoinKoineks): mdlCoin {
        val coin:mdlCoin
        val last = response.current
        val lowest = response.low
        val highest = response.high
        val volume = response.volume
        val currency = enmCoin.valueOf(response.currency!!)
        val name = response.name

        coin = mdlCoin(currency, last!!.toDouble(), lowest!!.toDouble(), highest!!.toDouble(), volume!!.toDouble(), name!!)

        return coin
    }

    private fun convertSistemKoinToCoin(response: JSONObject): mdlCoin? {
        val currencyCode = response.getString("currency")

        enumValues<enmCoin>().find {
            it.name == currencyCode
        }?.let {
            val coin:mdlCoin
            val last = response.getString("current").toDoubleOrNull()
            val lowest = response.getString("low").toDoubleOrNull()
            val highest = response.getString("high").toDoubleOrNull()
            val volume = response.getString("volume").toDoubleOrNull()
            val currency = it
            val name = response.getString("name")

            coin = mdlCoin(currency, last!!.toDouble(), lowest!!.toDouble(), highest!!.toDouble(), volume!!.toDouble(), name!!)

            return coin
        }

        return null
    }

    fun cancelAll() {
        provideDispatcher.let {
            provideDispatcher.cancelAll()
        }
        compositeDisposable.dispose()
    }

    internal fun setHost(host: String) {
        if (lastHost.isEmpty() || !lastHost.equals(host, ignoreCase = true)){
            this.lastHost = host
            provideUrlProvider.setNewRestApi(host)
        }

    }

    internal fun coinFilters(coinFilters: Array<enmCoin>) {
        this.coinFilters = coinFilters
    }


}