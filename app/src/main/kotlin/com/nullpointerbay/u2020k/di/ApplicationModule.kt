package com.nullpointerbay.u2020k.di

import android.content.Context
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance


fun applicationModule(context: Context) = Kodein.Module {
    bind<Context>() with instance(context)
}