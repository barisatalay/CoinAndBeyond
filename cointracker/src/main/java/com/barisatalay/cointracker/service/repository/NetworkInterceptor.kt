package com.barisatalay.cointracker.service.repository

import android.content.Context
import android.util.Log
import com.barisatalay.cointracker.helper.UtilsNetwork
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkInterceptor(private val mContext: Context): Interceptor {
    private val TAG = this.javaClass.simpleName
    private val NullNetwork: String = "Your Network Connection has been lost"

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        if (!UtilsNetwork.networkControl(mContext)) {
            Log.d(TAG, NullNetwork)
            throw IOException(NullNetwork)
        }

        return chain.proceed(request)
    }
}