package com.barisatalay.cointracker.service.model

class mdlParibu {
    private var BTC_TL: mdlCoinParibu? = null
    private var XRP_TL: mdlCoinParibu? = null
    private var ETH_TL: mdlCoinParibu? = null
    private var XLM_TL: mdlCoinParibu? = null
    private var ADA_TL: mdlCoinParibu? = null
    private var LTC_TL: mdlCoinParibu? = null
    private var EOS_TL: mdlCoinParibu? = null

    fun getBTCTL(): mdlCoinParibu? {
        return BTC_TL
    }

    fun setBTCTL(bTCTL: mdlCoinParibu) {
        this.BTC_TL = bTCTL
    }

    fun getXRPTL(): mdlCoinParibu? {
        return XRP_TL
    }

    fun setXRPTL(xRPTL: mdlCoinParibu) {
        this.XRP_TL = xRPTL
    }

    fun getETHTL(): mdlCoinParibu? {
        return ETH_TL
    }

    fun setETHTL(eTHTL: mdlCoinParibu) {
        this.ETH_TL = eTHTL
    }

    fun getXLMTL(): mdlCoinParibu? {
        return XLM_TL
    }

    fun setXLMTL(xLMTL: mdlCoinParibu) {
        this.XLM_TL = xLMTL
    }

    fun getADATL(): mdlCoinParibu? {
        return ADA_TL
    }

    fun setADATL(aDATL: mdlCoinParibu) {
        this.ADA_TL = aDATL
    }

    fun getLTCTL(): mdlCoinParibu? {
        return LTC_TL
    }

    fun setLTCTL(lTCTL: mdlCoinParibu) {
        this.LTC_TL = lTCTL
    }

    fun getEOSTL(): mdlCoinParibu? {
        return EOS_TL
    }

    fun setEOSTL(eOSTL: mdlCoinParibu) {
        this.EOS_TL = eOSTL
    }

}