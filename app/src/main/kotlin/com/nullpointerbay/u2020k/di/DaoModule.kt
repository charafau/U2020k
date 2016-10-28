package com.nullpointerbay.u2020k.di

import android.util.Log
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.nullpointerbay.u2020k.dao.RepoDao
import com.nullpointerbay.u2020k.dao.RepoDaoImpl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by rafal on 10/26/16.
 */

fun daoModule() = Kodein.Module {
    bind<OkHttpClient>() with provider {

        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
        .addInterceptor(logger)
        .build()
        builder

    }
    bind<RepoDao>() with provider { RepoDaoImpl(instance()) }
    bind<Retrofit>() with provider {
        Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(instance())
                .build()
    }
}

