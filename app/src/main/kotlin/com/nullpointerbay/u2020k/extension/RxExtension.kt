package com.nullpointerbay.u2020k.extension

import rx.Observable
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


fun <T> Observable<T>.subIoObserveMain(ioScheduler: Scheduler, mainThreadScheduler: Scheduler): Observable<T> {
    return this.subscribeOn(ioScheduler).observeOn(mainThreadScheduler)
}