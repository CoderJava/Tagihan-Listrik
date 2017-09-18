package com.tagihanlistrik.ysn.views.main

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.nikoyuwono.toolbarpanel.ToolbarPanelLayout
import com.tagihanlistrik.ysn.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.panel_data_tagihan.view.*

class MainActivity : AppCompatActivity(), MainView, View.OnClickListener {

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
        initListeners()
        doLoadData()
    }

    private fun initToolbar() {
        toolbar_activity_main.title = getString(R.string.app_name)
        setSupportActionBar(toolbar_activity_main)
    }

    private fun initViews() {
        toolbar_panel_layout.lockMode = ToolbarPanelLayout.LOCK_MODE_LOCKED_OPEN
    }

    private fun initListeners() {
        button_check_the_bill_activity_main
                .setOnClickListener(this)
        include_panel_data_bill_activity_main
                .button_simpan_panel_data_tagihan
                .setOnClickListener(this)
        include_panel_data_bill_activity_main
                .button_batal_panel_data_tagihan
                .setOnClickListener(this)
        relative_layout_container_user_folder_activity_main
                .setOnClickListener(this)
    }

    private fun doLoadData() {
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Data tagihan")
        progressDialog.setCancelable(false)
        mainPresenter?.onLoadData(this)
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

    override fun loadData(runService: String, customerId: String) {
        if (runService.equals("enabled", true)) {
            // todo: do something in here
            /*startService(Intent(this, ServiceTagihanListrik::class.java))*/
        }

        if (!customerId.equals("not set", true) && runService.equals("enabled", true)) {
            edit_text_customer_id_activity_main.setText(customerId)
        }

        val bundle = intent.extras
        if (bundle != null) {
            val customerId = bundle.getString("customerId")
            edit_text_customer_id_activity_main.setText(customerId)
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.button_check_the_bill_activity_main -> {
                val customerId = edit_text_customer_id_activity_main
                        .text
                        .toString()
                        .trim()
                val phoneNumber = edit_text_phone_number_activity_main
                        .text
                        .toString()
                        .trim()
                when {
                    customerId.isEmpty() -> Toast.makeText(
                            this@MainActivity,
                            "Customer ID is empty",
                            Toast.LENGTH_SHORT
                    ).show()
                    phoneNumber.isEmpty() -> Toast.makeText(
                            this@MainActivity,
                            "Phone Number is empty",
                            Toast.LENGTH_SHORT
                    ).show()
                    else -> mainPresenter?.onCheckTheBill(
                            customerId = customerId,
                            phoneNumber = phoneNumber
                    )
                }
            }
            R.id.button_simpan_panel_data_tagihan -> {
                // todo: do something in here
            }
            R.id.button_batal_panel_data_tagihan -> {
                // todo: do something in here
            }
            R.id.relative_layout_container_user_folder_activity_main -> {
                // todo: do something in here
            }
            else -> {
                /** nothing to do in here */
            }
        }
    }

}
