package com.barisatalay.cointracker.service.repository

import io.reactivex.Single
import retrofit2.http.GET

interface RestfulRequest {
    @GET("/ticker")
    fun GetParibu():Single<String>

    @GET("/ticker")
    fun GetKoineks():Single<String>

    @GET("/api/ticker")
    fun GetBtcTurk():Single<String>

    @GET("/api/market/ticker")
    fun GetSistemKoin():Single<String>
}