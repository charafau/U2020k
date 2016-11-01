package com.nullpointerbay.u2020k.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.nullpointerbay.u2020k.base.FragmentBaseActivity
import com.nullpointerbay.u2020k.fragment.DetailFragment
import com.nullpointerbay.u2020k.fragment.MainFragment
import com.nullpointerbay.u2020k.model.Repo


class MainActivity : FragmentBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        root(MainFragment.getInstance())

    }
}

class DetailActivity : FragmentBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repo = intent.extras.getParcelable<Repo>(ARG_REPO)

        root(DetailFragment.getInstance(repo))

    }

    companion object {
        fun start(context: Context, repo: Repo){
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(ARG_REPO, repo)
            context.startActivity(intent)
        }

        val ARG_REPO = "repo"

    }


}