package com.barisatalay.cointracker.helper

import android.content.Context
import android.net.ConnectivityManager
import com.barisatalay.cointracker.service.repository.NetworkInterceptor
import com.barisatalay.cointracker.service.repository.ProjectRepository
import com.barisatalay.cointracker.service.repository.RestfulRequest
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.lang.reflect.Modifier
import java.util.concurrent.TimeUnit

class UtilsNetwork {
    companion object {
        fun provideGson(): Gson {
            val exclusionStrategy = object : ExclusionStrategy {

                override fun shouldSkipField(fieldAttributes: FieldAttributes): Boolean {
                    return false
                }

                override fun shouldSkipClass(clazz: Class<*>): Boolean {
                    return clazz == Field::class.java || clazz == Method::class.java
                }
            }

            val builder = GsonBuilder()
            builder.addSerializationExclusionStrategy(exclusionStrategy)
                .setLenient()
                .serializeNulls()
                .addDeserializationExclusionStrategy(exclusionStrategy)
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .serializeNulls()


            return builder.create()
        }
        fun networkControl(ctx: Context): Boolean {
            val conMgr = ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val i = conMgr.activeNetworkInfo

            return i != null && i.isConnected && i.isAvailable
        }

        private fun provideUrlProvider(): UrlProviderInterceptor {
            return UrlProviderInterceptor()
        }

        private fun provideOkHttpClient(
            mDispatcher: Dispatcher,
            urlProviderInterceptor: UrlProviderInterceptor
        ): OkHttpClient {
            return OkHttpClient().newBuilder()
                .addInterceptor(urlProviderInterceptor)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .dispatcher(mDispatcher)
                .build()
        }

        private fun providesRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://www.paribu.com")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
        }

        private fun provideRestfulRequest(retrofit: Retrofit): RestfulRequest {
            return retrofit.create<RestfulRequest>(RestfulRequest::class.java)
        }
        fun provideProjectRepository(): ProjectRepository {
            val provideDispatcher = provideDispatcher()

            val provideUrlProvider = provideUrlProvider()

            val provideOkHttpClient = provideOkHttpClient(provideDispatcher, provideUrlProvider)

            val providesRetrofit = providesRetrofit(provideOkHttpClient, provideGson())

            return ProjectRepository(provideRestfulRequest(providesRetrofit), provideScheduler(), provideDispatcher, provideUrlProvider)
        }
        private fun provideDispatcher(): Dispatcher {
            return Dispatcher()
        }

        private fun provideScheduler(): Scheduler {
            return Schedulers.io()
        }
    }

}