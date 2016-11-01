package com.nullpointerbay.u2020k.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.salomonbrys.kodein.KodeinInjected
import com.github.salomonbrys.kodein.KodeinInjector
import com.nullpointerbay.u2020k.R
import org.jetbrains.anko.*

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

abstract class FragmentBaseActivity : BaseActivity() {
    protected fun root(fragment: BaseFragment) {
        verticalLayout {
            lparams(width = matchParent, height = matchParent)
            val toolbar = toolbar(theme = R.style.ThemeOverlay_AppCompat_Dark_ActionBar) {
                lparams(width = matchParent, height = wrapContent)
                backgroundResource = R.color.colorPrimary
                popupTheme = R.style.ThemeOverlay_AppCompat_Light

            }
            setActionBar(toolbar)
            val frameId = View.generateViewId()

            frameLayout {
                id = frameId
                lparams(width = matchParent, height = wrapContent)
            }

            supportFragmentManager.beginTransaction().replace(frameId, fragment, fragment.tag).commit()

        }
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