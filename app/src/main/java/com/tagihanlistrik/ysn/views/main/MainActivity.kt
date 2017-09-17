package com.tagihanlistrik.ysn.views.main

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nikoyuwono.toolbarpanel.ToolbarPanelLayout
import com.tagihanlistrik.ysn.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    private val TAG = javaClass.simpleName
    private var mainPresenter: MainPresenter? = null
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initPresenter()
        onAttach()
        initToolbar()
        initViews()
        doLoadData()
    }

    private fun initToolbar() {
        toolbar_activity_main.title = getString(R.string.app_name)
        setSupportActionBar(toolbar_activity_main)
    }

    private fun initViews() {
        toolbar_panel_layout.lockMode = ToolbarPanelLayout.LOCK_MODE_LOCKED_OPEN
    }

    private fun doLoadData() {
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Data tagihan")
        progressDialog.setCancelable(false)
        mainPresenter?.onLoadData()
    }

    private fun initPresenter() {
        mainPresenter = MainPresenter()
    }

    override fun onResume() {
        onAttach()
        super.onResume()
    }

    override fun onDestroy() {
        onDetach()
        super.onDestroy()
    }

    override fun onAttach() {
        mainPresenter?.onAttachView(this)
    }

    override fun onDetach() {
        mainPresenter?.onDetachView()
    }
}
