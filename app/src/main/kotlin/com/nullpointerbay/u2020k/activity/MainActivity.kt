package com.nullpointerbay.u2020k.activity

import android.os.Bundle
import com.nullpointerbay.u2020k.R
import com.nullpointerbay.u2020k.base.BaseActivity
import com.nullpointerbay.u2020k.fragment.MainFragment

class MainActivity : BaseActivity() {

    lateinit var mainFragment: MainFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)

        initToolbar()
        initFragment()
    }

    private fun initFragment() {
        val ft = supportFragmentManager.beginTransaction()
        mainFragment = MainFragment.getInstance()
        ft.replace(R.id.root, mainFragment, mainFragment.tag)
        ft.commit()

    }
}
