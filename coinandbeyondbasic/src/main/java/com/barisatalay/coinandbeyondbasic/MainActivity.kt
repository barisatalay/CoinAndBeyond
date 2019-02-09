package com.barisatalay.coinandbeyondbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.barisatalay.cointracker.CoinAndBeyond
import com.barisatalay.cointracker.data.SistemKoin
import com.barisatalay.cointracker.service.model.enmCoin
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val myObject = CoinAndBeyond(SistemKoin(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myObject.setCoinFilter(arrayOf(enmCoin.BTC,enmCoin.ETH,enmCoin.TRX))

        myObject.getCoins(Observer {response->
            val builder = StringBuilder()
            for(coin in response.getCoinDetail()) {
                builder.append("...:::${coin.key}:::...").append("\n")
                Log.i("CoinAndBeyondBasic", "Coin Type: ...:::${coin.key}:::...")
                for (row in coin.value) {
                    builder.append("${row.currency} : ${row.last}").append("\n").append("\n")

                    Log.i("CoinAndBeyondBasic", "${row.currency} : ${row.last}")
                }
            }

            infoTxt.text = builder.toString()
        })
    }
}
