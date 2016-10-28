package com.nullpointerbay.u2020k.base

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinInjected
import com.github.salomonbrys.kodein.KodeinInjector
import com.nullpointerbay.u2020k.di.daoModule

/**
 * Created by charafau on 2016/10/28.
 */
abstract class BaseDao() : KodeinInjected {
    override val injector = KodeinInjector()

    init {
        injector.inject(Kodein {
            import(daoModule())
        })
    }

}