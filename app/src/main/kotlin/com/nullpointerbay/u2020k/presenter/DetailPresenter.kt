package com.nullpointerbay.u2020k.presenter

import com.nullpointerbay.u2020k.base.BasePresenter
import com.nullpointerbay.u2020k.base.BaseView
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

interface DetailPresenter {

}

interface DetailView : BaseView {

}

class DetailPresenterImpl(val detailView: DetailView, val ioScheduler: Scheduler = Schedulers.io(),
                          val mainThreadScheduler: Scheduler = AndroidSchedulers.mainThread()) : BasePresenter(), DetailPresenter {

}