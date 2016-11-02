package com.nullpointerbay.u2020k.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.android.appKodein
import com.nullpointerbay.u2020k.R
import com.nullpointerbay.u2020k.base.BaseFragment
import com.nullpointerbay.u2020k.base.CircleTransform
import com.nullpointerbay.u2020k.model.Repo
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.UI


class DetailFragment : BaseFragment() {

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
                val IMG_ID = View.generateViewId()
                val NAME_ID = View.generateViewId()

                padding = dip(16)
                val img = imageView {
                    id = IMG_ID
                    lparams(width = dip(40), height = dip(40))
                }

                textView {
                    text = repo.name
                    id = NAME_ID
                }.lparams {
                    rightOf(IMG_ID)
                    leftMargin = dip(16)
                }

                textView {
                    text = repo.fullName
                }.lparams {
                    rightOf(IMG_ID)
                    leftMargin = dip(16)
                    below(NAME_ID)
                }

                if (repo.owner.avatarUrl != null) {
                    Picasso.with(context).load(repo.owner.avatarUrl).transform(CircleTransform()).into(img)
                }else{
                    img.setImageResource(R.drawable.avatar)
                }
            }

        }.view

    }
}