package com.nullpointerbay.u2020k.model

import com.google.gson.annotations.SerializedName

//TODO: add PaperParcel

data class ApiSummary(
        @SerializedName("total_count")
        val totalCount: Int,
        @SerializedName("incomplete_results")
        val incompleteResults: Boolean,
        @SerializedName("items")
        val items: List<Repo>)


data class Repo(
        val id: Int,
        val name: String,
        @SerializedName("full_name")
        val fullName: String,
        @SerializedName("html_url")
        val htmlUrl: String,
        @SerializedName("owner")
        val owner: User,
        @SerializedName("stargazers_count")
        val stargazersCount: Int,
        @SerializedName("watchers_count")
        val watchersCount: Int,
        @SerializedName("forks")
        val forks: Int,
        @SerializedName("language")
        val language: String)


data class User(
        val login: String,
        val id: Int,
        @SerializedName("avatar_url")
        val avatarUrl: String?,
        val url: String,
        @SerializedName("html_url")
        val htmlUrl: String,
        @SerializedName("followers_url")
        val followersUrl: String)