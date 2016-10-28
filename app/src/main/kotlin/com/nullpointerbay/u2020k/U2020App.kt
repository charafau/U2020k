package com.nullpointerbay.u2020k

import android.app.Application
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.lazy
import com.nullpointerbay.u2020k.di.applicationModule

class U2020App : Application(), KodeinAware {

    override val kodein by Kodein.lazy {

        import(applicationModule(this@U2020App))

    }


}