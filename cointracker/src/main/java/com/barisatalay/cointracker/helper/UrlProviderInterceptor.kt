package com.barisatalay.cointracker.helper

import android.text.TextUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.*

class UrlProviderInterceptor: Interceptor {

    private val TAG = "UrlProviderInterceptor"
    private var newRestApi: String? = null

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        if (!TextUtils.isEmpty(newRestApi)) {
            val newUrl =
                ArrayList(Arrays.asList(*newRestApi!!.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()))

            val newScheme = newUrl[0].replace(":", "")
            val newHost = newUrl[2]

            val urlBuilder = request.url().newBuilder()
                .scheme(newScheme)
                .host(newHost)

            request = request.newBuilder()
                .url(urlBuilder.build())
                .build()


//            val activeUrl = request.url().toString().substring(0, request.url().toString().indexOf("api/Method"))
//            if (!activeUrl.equals(newRestApi!!, ignoreCase = true)) {
//                val logger = StringBuilder()
//                logger.append("\n\n********************************\n")
//                logger.append("Old Api Address: ").append(activeUrl)
//                val baseEncodedArray =
//                    request.url().toString().substring(request.url().toString().indexOf("api/Method"))
//                        .split("/".toRegex()).dropLastWhile { it.isEmpty() }
//                        .toTypedArray()
//                val baseSegmentList = ArrayList(Arrays.asList(*baseEncodedArray))
//
//                val newUrl =
//                    ArrayList(Arrays.asList(*newRestApi!!.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()))
//
//                val newScheme = newUrl[0].replace(":", "")
//                val newHost = newUrl[2]
//
//                val urlBuilder = request.url().newBuilder()
//                    .scheme(newScheme)
//                    .host(newHost)
//                for (i in request.url().encodedPathSegments().size - 1 downTo 0)
//                    urlBuilder.removePathSegment(i)
//
//                if (newUrl.size > 3) {
//                    for (i in newUrl.size - 1 downTo 3)
//                        urlBuilder.addEncodedPathSegment(newUrl[i])
//                }
//
//                for (i in baseSegmentList.indices)
//                    urlBuilder.addEncodedPathSegment(baseSegmentList[i])
//
//                request = request.newBuilder()
//                    .url(urlBuilder.build())
//                    .build()
//
//                logger.append("\nNew Api Address: ").append(newRestApi)
//                logger.append("\n********************************\n")
//
//                Log.i(TAG, logger.toString())
//            }



        }
        return chain.proceed(request)
    }

    fun setNewRestApi(newRestApi: String) {
        this.newRestApi = newRestApi
    }

}