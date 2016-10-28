package com.nullpointerbay.u2020k.presenter

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nullpointerbay.u2020k.dao.RepoDao
import com.nullpointerbay.u2020k.model.ApiSummary
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import rx.Observable
import rx.schedulers.Schedulers

@RunWith(JUnitPlatform::class)
class MainPresenterTest : Spek({

    val mockView = mock<MainView> {

    }

    val mockRepo = mock<RepoDao> {
        on { downloadSomething() } doReturn Observable.just(ApiSummary(1, true, listOf()))
    }

    describe("Main Presenter") {

        val mainPresenter = MainPresenterImpl(mockView, mockRepo, Schedulers.immediate(), Schedulers.immediate())

        it("should call repo dao") {

            mainPresenter.loadRepos()

            verify(mockRepo).downloadSomething()
            verify(mockView).showRepos(any())
        }
    }
})