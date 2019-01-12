package com.barisatalay.cointrackersample

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.barisatalay.cointracker.CoinAndBeyond
import com.barisatalay.cointracker.data.*
import com.barisatalay.cointracker.service.model.enmCoin
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    private val paribu = CoinAndBeyond(Paribu())
    private val koineks = CoinAndBeyond(Koineks())
    private val btcTurk = CoinAndBeyond(BtcTurk())
    private val sistemKoin = CoinAndBeyond(SistemKoin())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hideProgress()

        paribuBtn.setOnClickListener {
            showProgress()
            paribu.setCoinFilter(getSelectedCoins())
            paribu.getRepository().GetKoineks(getSelectedCoins(), object : IResponse{
                override fun onResponse(responseData: mdlCoinResponse) {
                    responseTxt.text = prepareCoinTexts(responseData)
                    hideProgress()
                }
            })
        }

        koineksBtn.setOnClickListener {
            showProgress()
            koineks.setCoinFilter(getSelectedCoins())
            koineks.getRepository().GetKoineks(getSelectedCoins(), object : IResponse{
                override fun onResponse(responseData: mdlCoinResponse) {
                    runOnUiThread {
                        responseTxt.text = prepareCoinTexts(responseData)
                        hideProgress()
                    }
                }
            })
        }

        btcturkBtn.setOnClickListener {
            showProgress()
            btcTurk.setCoinFilter(getSelectedCoins())
            btcTurk.getRepository().GetKoineks(getSelectedCoins(), object : IResponse{
                override fun onResponse(responseData: mdlCoinResponse) {
                    runOnUiThread {
                        responseTxt.text = prepareCoinTexts(responseData)
                        hideProgress()
                    }
                }
            })
        }

        sistemKoinBtn.setOnClickListener {
            showProgress()
            sistemKoin.setCoinFilter(getSelectedCoins())
            sistemKoin.getRepository().GetKoineks(getSelectedCoins(), object : IResponse{
                override fun onResponse(responseData: mdlCoinResponse) {
                    runOnUiThread {
                        responseTxt.text = prepareCoinTexts(responseData)
                        hideProgress()
                    }
                }
            })
        }
    }

    private fun getSelectedCoins(): Array<enmCoin> {
        val result: ArrayList<enmCoin> = arrayListOf()

        if (btcChk.isChecked)
            result.add(enmCoin.BTC)

        if (xrpChk.isChecked)
            result.add(enmCoin.XRP)

        if (ethChk.isChecked)
            result.add(enmCoin.ETH)


        return result.toTypedArray()
    }

    private fun prepareCoinTexts(coinList: mdlCoinResponse): String {
        val builder = StringBuilder()

        coinList.getCoinDetail().mapKeys {mapItem->
            for (coinItem in mapItem.value) {
                builder.append("${coinItem.description}(${mapItem.key.name}): ${coinItem.last} ${coinItem.currency.name}").append("\n")
            }
        }
        Log.i("MainActivity","\n")
        Log.i("MainActivity","****************************************************")
        Log.i("MainActivity","$builder")
        Log.i("MainActivity","****************************************************")
        Log.i("MainActivity","\n")
        return builder.toString()
    }

    private fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        koineks.cancelAllRequests()
        paribu.cancelAllRequests()
        btcTurk.cancelAllRequests()
        sistemKoin.cancelAllRequests()

        mdlCoinResponse()
    }
}
