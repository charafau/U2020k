package com.nullpointerbay.u2020k.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.description
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.kodein
import com.nullpointerbay.u2020k.R
import com.nullpointerbay.u2020k.base.BaseFragment
import com.nullpointerbay.u2020k.base.CircleTransform
import com.nullpointerbay.u2020k.dao.RepoDao
import com.nullpointerbay.u2020k.di.daoModule
import com.nullpointerbay.u2020k.di.mainPrestenterModule
import com.nullpointerbay.u2020k.extension.inflate
import com.nullpointerbay.u2020k.model.Repo
import com.nullpointerbay.u2020k.presenter.MainPresenter
import com.nullpointerbay.u2020k.presenter.MainView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.find

class MainFragment : BaseFragment(), MainView {

    override fun showRepos(items: List<Repo>) {
        repoAdapter?.showItems(items)
        progress_bar.visibility = View.GONE
        repoAdapter?.notifyDataSetChanged()
    }

    private var repoAdapter: RepoAdapter? = null

    override fun inject() {
        injector.inject(Kodein {
            extend(appKodein())
            import(daoModule())
            import(mainPrestenterModule(this@MainFragment))

        })
    }

    override fun printValue(testRepo: String) {
        Log.e("test", testRepo)
    }

    private val presenter: MainPresenter by instance()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return container?.inflate(R.layout.fragment_main)!!
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repoAdapter = RepoAdapter(listOf())
        recycler.adapter = repoAdapter
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.setHasFixedSize(true)

        Log.i("Kodein", appKodein().container.bindings.description)

    }

    override fun onResume() {
        super.onResume()
        presenter.loadRepos()
        Log.e("test", "I'm Main Fragment")
    }

    override fun tag() = "MainFragment"

    companion object {
        fun getInstance(): MainFragment = MainFragment()
    }

}

class RepoAdapter(var repos: List<Repo>) : RecyclerView.Adapter<RepoAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val repo = repos[position]
        holder!!.bind(repo)
    }
//        return ViewHolder(RepoItemUI().createView(AnkoContext.Companion.create(parent!!.context, parent)))

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = ViewHolder(parent?.inflate(R.layout.item_repo)!!)


    override fun getItemCount() = repos.size


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val txtRepo: TextView = itemView.find(R.id.trending_repository_name)
        val txtDesc: TextView = itemView.find(R.id.trending_repository_description)
        val txtForks: TextView = itemView.find(R.id.trending_repository_forks)
        val txtStars: TextView = itemView.find(R.id.trending_repository_stars)
        val imgAvatar: ImageView = itemView.find(R.id.trending_repository_avatar)


        fun bind(repo: Repo) {
            txtRepo.text = repo.name
            txtDesc.text = repo.fullName
            txtForks.text = repo.forks.toString()
            txtStars.text = repo.stargazersCount.toString()
            Picasso.with(itemView.context).load(repo.owner.avatarUrl).transform(CircleTransform()).into(imgAvatar)
        }


    }

    fun showItems(items: List<Repo>) {
        repos = items
    }

}

//class RepoItemUI : AnkoComponent<ViewGroup> {
//    override fun createView(ui: AnkoContext<ViewGroup>): View {
//        return with(ui) {
//            linearLayout {
//                lparams(width = matchParent, height = wrapContent)
//                orientation = LinearLayout.HORIZONTAL
//                padding = dip(16)
//                cardView {
//                    lparams(width = matchParent, height = wrapContent)
//
//                    textView {
//                        padding = dip(16)
//                        id = R.id.trending_repository_name
//                    }
//                }
//            }
//        }
//    }
//}