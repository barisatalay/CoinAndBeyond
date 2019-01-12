package com.barisatalay.cointracker.data

interface IResponse {
    fun onResponse(responseData: mdlCoinResponse)
}