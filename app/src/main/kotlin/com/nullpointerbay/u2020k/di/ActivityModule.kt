package com.nullpointerbay.u2020k.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.nullpointerbay.u2020k.dao.RepoDao
import com.nullpointerbay.u2020k.presenter.MainPresenter
import com.nullpointerbay.u2020k.presenter.MainPresenterImpl
import com.nullpointerbay.u2020k.presenter.MainView


fun mainPrestenterModule(view: MainView) = Kodein.Module {
    bind<MainPresenter>() with provider { MainPresenterImpl(view, instance()) }
}

