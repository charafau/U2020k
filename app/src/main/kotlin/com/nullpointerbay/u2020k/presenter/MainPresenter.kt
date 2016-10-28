package com.nullpointerbay.u2020k.presenter

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinInjected
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.instance
import com.nullpointerbay.u2020k.base.BaseView
import com.nullpointerbay.u2020k.dao.RepoDao
import com.nullpointerbay.u2020k.di.daoModule
import com.nullpointerbay.u2020k.model.Repo
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


interface MainPresenter {
    fun sayHi(): Unit
    fun loadRepos(): Unit
}


interface MainView : BaseView {
    fun printHi(): Unit
    fun printValue(testRepo: String)
    fun showRepos(items: List<Repo>)

}

class MainPresenterImpl(val view: MainView) : MainPresenter, KodeinInjected {

    override fun loadRepos() {
        repoDao.downloadSomething()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    apiSummary -> view.showRepos(apiSummary.items)
                }, {
                    view.printValue("wtf: ${it.message}")
                })
    }

    override val injector = KodeinInjector()

    init {
        injector.inject(Kodein {
            import(daoModule())
        })
    }

    val repoDao: RepoDao by instance()


    override fun sayHi() {
        view.printHi()
    }


}