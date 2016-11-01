package com.nullpointerbay.u2020k.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.android.appKodein
import com.nullpointerbay.u2020k.R
import com.nullpointerbay.u2020k.base.BaseFragment
import com.nullpointerbay.u2020k.extension.inflate
import com.nullpointerbay.u2020k.model.Repo
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.UI


class DetailFragment: BaseFragment() {

    override fun inject() {
        injector.inject(Kodein {
            extend(appKodein())
        })
    }

    companion object {
        fun getInstance(repo: Repo): DetailFragment {
            val bundle = Bundle()
            bundle.putParcelable(ARG_REPO, repo)
            val detailFragment = DetailFragment()
            detailFragment.arguments = bundle

            return detailFragment

        }

        val ARG_REPO = "repo"
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val repo = arguments.getParcelable<Repo>(ARG_REPO)

        return UI {
            relativeLayout {
                verticalLayout {
                   textView {
                       text = repo.fullName

                   }
                }
            }
        }.view

    }
}