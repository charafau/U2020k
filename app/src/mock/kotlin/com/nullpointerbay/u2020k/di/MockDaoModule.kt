package com.nullpointerbay.u2020k.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.provider
import com.nullpointerbay.u2020k.dao.RepoDao
import com.nullpointerbay.u2020k.model.ApiSummary
import com.nullpointerbay.u2020k.model.Repo
import com.nullpointerbay.u2020k.model.User
import rx.Observable

fun daoModule() = Kodein.Module {

    bind<RepoDao>() with provider { MockRepoDao() }

}

/**
 * it is good idea to create some factories for those files
 */
class MockRepoDao() : RepoDao {
    override fun downloadSomething(): Observable<ApiSummary> {


        val owner = User("Bob", 1, null, "http://github.com/bob", "http://github.com/bob", "http://github.com/followers/bob")
        val elements = Repo(1, "First", "First Repository", "http://url.com", owner, 3, 4, 5, "pl")
        val apiSummary = ApiSummary(2, true, arrayListOf(elements))

        return Observable.just(apiSummary)

    }

}

