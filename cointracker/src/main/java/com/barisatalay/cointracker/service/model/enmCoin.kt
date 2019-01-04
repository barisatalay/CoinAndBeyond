package com.barisatalay.cointracker.service.model

enum class enmCoin(private val fullName:String) {
    TL("Türk Lirası"),
    TRY("Türk Lirası"),
    EUR("Euro"),
    EURO("Euro"),
    BTC("BitCoin"),
    XRP("Ripple"),
    ETH("Ethereum"),
    XLM("Stellar"),
    ADA("Cardano"),
    LTC("Litecoin"),
    EOS("EOS"),
    USDT("Tether"),
    USD("Amerikan Doları"),
    BCH("Bitcoin Cash"),
    DOGE("Dogecoin"),
    DASH("DASH"),
    XEM("NEM"),
    BTG("Bitcoin Gold"),
    ETC("Ethereum Classic"),
    TRX("TRON"),
    ZEC("Zcash"),
    DGB("DigiByte"),
    BEN("Benjamins"),
    XIN("Infinity Economics"),
    WRC("Worldcore"),
    SYS("Syscoin"),
    WPR("WePower"),
    XSN("Stakenet"),
    BTW("BitWhite"),
    DRG("Dragon Coins"),
    LNC("Linker Coin"),
    MTC("Marinecoin"),
    MEDIC("MedicCoin"),
    BTCP("Bitcoin Private"),
    RLX("Relex"),
    HBZ("Helbiz"),
    PIRL("Pirl");

    override fun toString():String{
        return fullName
    }
}