package com.barisatalay.cointracker.helper

import android.util.Log
import io.reactivex.observers.DisposableSingleObserver

abstract class BaseObserver<T>: DisposableSingleObserver<T>() {


    override fun onSuccess(response: T?) {
        response?.let {
            onResponse(it)
            Log.i("CoinAndBeyond", "Response Data: $it.toString()")
            return
        }
        Log.e("CoinAndBeyond", "Response Data null")

    }

    override fun onError(e: Throwable) {
        Log.e("CoinAndBeyond", e.message)
        onResponseError(e)
    }

    abstract fun onResponse(response: T)
    abstract fun onResponseError(e: Throwable)
}