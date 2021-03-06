package com.nullpointerbay.u2020k.presenter

import com.github.salomonbrys.kodein.KodeinInjected
import com.nullpointerbay.u2020k.base.BasePresenter
import com.nullpointerbay.u2020k.base.BaseView
import com.nullpointerbay.u2020k.dao.RepoDao
import com.nullpointerbay.u2020k.extension.subIoObserveMain
import com.nullpointerbay.u2020k.model.Repo
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


interface MainPresenter {
    fun loadRepos(): Unit
    fun clickRepo(repo: Repo)
}


interface MainView : BaseView {
    fun printValue(testRepo: String)
    fun showRepos(items: List<Repo>)
    fun navigateToDetailView(repo: Repo)
}

class MainPresenterImpl(val view: MainView, val repoDao: RepoDao, val ioScheduler: Scheduler = Schedulers.io(),
                        val mainThreadScheduler: Scheduler = AndroidSchedulers.mainThread()) : BasePresenter(), MainPresenter, KodeinInjected {

    override fun clickRepo(repo: Repo) {
        view.navigateToDetailView(repo)
    }

    override fun loadRepos() {
        //TODO: add composite subscription
        repoDao.downloadSomething()
                .subIoObserveMain(ioScheduler, mainThreadScheduler)
                .subscribe({
                    apiSummary ->
                    view.showRepos(apiSummary.items)
                }, {
                    view.printValue("wtf: ${it.message}")
                })
    }

}