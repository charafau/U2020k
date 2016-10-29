package com.nullpointerbay.u2020k.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.nullpointerbay.u2020k.dao.RepoDao
import com.nullpointerbay.u2020k.dao.RepoDaoImpl

fun daoModule() = Kodein.Module {

    bind<RepoDao>() with provider { RepoDaoImpl(instance()) }

}

