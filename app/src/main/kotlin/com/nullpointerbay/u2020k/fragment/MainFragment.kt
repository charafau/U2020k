package com.nullpointerbay.u2020k.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.description
import com.github.salomonbrys.kodein.instance
import com.nullpointerbay.u2020k.R
import com.nullpointerbay.u2020k.base.BaseFragment
import com.nullpointerbay.u2020k.di.mainPrestenterModule
import com.nullpointerbay.u2020k.model.Repo
import com.nullpointerbay.u2020k.presenter.MainPresenter
import com.nullpointerbay.u2020k.presenter.MainView
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.*
import com.nullpointerbay.u2020k.extension.*

class MainFragment : BaseFragment(), MainView {

    override fun showRepos(items: List<Repo>) {
        repoAdapter?.showItems(items)
        repoAdapter?.notifyDataSetChanged()
    }

    private var repoAdapter: RepoAdapter? = null

    override fun inject() {
        injector.inject(Kodein {
            extend(appKodein())
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

        repoAdapter  = RepoAdapter(listOf())
        recycler.adapter = repoAdapter
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.setHasFixedSize(true)
        presenter.sayHi()

        Log.i("Kodein", appKodein().container.bindings.description)


    }

    override fun onResume() {
        super.onResume()
        presenter.loadRepos()
        Log.e("test", "I'm Main Fragment")
    }

    override fun printHi() {
        Log.e("test", "hi hi hi ")
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

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(RepoItemUI().createView(AnkoContext.Companion.create(parent!!.context, parent)))
    }

    override fun getItemCount(): Int = repos.size


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val txtRepo: TextView = itemView.find(R.id.txt_repo)

        fun bind(repo: Repo) {
            txtRepo.text = repo.name
        }


    }

    fun showItems(items: List<Repo>) {
        repos = items
    }

}

class RepoItemUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                orientation = LinearLayout.HORIZONTAL
                padding = dip(16)
                textView {
                    id = R.id.txt_repo
                }
            }
        }
    }
}