package com.nullpointerbay.u2020k.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinInjected
import com.github.salomonbrys.kodein.KodeinInjector
import com.nullpointerbay.u2020k.R
import com.nullpointerbay.u2020k.di.daoModule

interface BaseView {
    fun tag(): String
}

abstract class BaseActivity : AppCompatActivity() {

    protected fun initToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}

abstract class BaseFragment : Fragment(), KodeinInjected {

    override val injector = KodeinInjector()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        inject()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    abstract fun inject(): Unit

}

abstract class BasePresenter() : KodeinInjected {

    override val injector = KodeinInjector()

}