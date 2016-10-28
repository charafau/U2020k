package com.nullpointerbay.u2020k.dao

import com.nullpointerbay.u2020k.base.BaseDao
import com.nullpointerbay.u2020k.model.ApiSummary
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface RepoDao {
    fun downloadSomething(): Observable<ApiSummary>
}

class RepoDaoImpl(retrofit: Retrofit) : BaseDao(), RepoDao {

    private val githubApi: GithubApi

    init {
        githubApi = retrofit.create(GithubApi::class.java)
    }

    override fun downloadSomething() = githubApi.getRepos("created", "stars", "desc")
}

interface GithubApi {

    @GET("search/repositories")
    fun getRepos(@Query("q") query: String,
                 @Query("sort") sort: String,
                 @Query("order") order: String): Observable<ApiSummary>

}