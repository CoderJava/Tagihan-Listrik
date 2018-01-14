package com.tagihanlistrik.ysn.views.ui.billinfo

import android.app.Dialog
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialogFragment
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.view.View
import com.tagihanlistrik.ysn.R
import com.tagihanlistrik.ysn.di.component.billinfo.DaggerBillBottomSheetDialogFragmentComponent
import com.tagihanlistrik.ysn.di.module.billinfo.BillBottomSheetDialogFragmentModule
import com.tagihanlistrik.ysn.model.bill.Data
import com.tagihanlistrik.ysn.views.base.BaseBottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_dialog_fragment_data_bill.view.*
import java.text.DecimalFormat
import javax.inject.Inject

/**
 * Created by yudisetiawan on 1/13/18.
 */
class BillBottomSheetDialogFragment : BaseBottomSheetDialogFragment(), BillView, View.OnClickListener {

    val TAG = javaClass.simpleName

    @Inject
    lateinit var presenter: BillPresenter

    private lateinit var billData: Data

    override fun setupDialog(dialog: Dialog?, style: Int) {
        super.setupDialog(dialog, style)
        val view = View.inflate(
                context,
                R.layout.bottom_sheet_dialog_fragment_data_bill,
                null
        )
        dialog?.setContentView(view)
        initViews(view)
        initListeners(view)
        doLoadData(view)
    }

    override fun onBottomSheetDialogFragmentInject() {
        DaggerBillBottomSheetDialogFragmentComponent.builder()
                .appComponent(getAppComponent())
                .billBottomSheetDialogFragmentModule(BillBottomSheetDialogFragmentModule())
                .build()
                .inject(this)
        presenter.attachView(this)
    }

    override fun onError() {
        // TODO: do something in here if needed
    }

    private fun initViews(view: View) {
        val layoutParams = (view.parent as View).layoutParams as CoordinatorLayout.LayoutParams
        val behavior = layoutParams.behavior
        (behavior as BottomSheetBehavior).isHideable = false
        behavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // TODO: do something in here if needed
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                behavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        })
        view.relative_layout_container_bottom_sheet_dialog_fragment_data_bill.post {
            val heightContainer = view.relative_layout_container_bottom_sheet_dialog_fragment_data_bill.height
            behavior.peekHeight = heightContainer
        }
    }

    private fun initListeners(view: View) {
        view.button_close_bottom_sheet_dialog_fragment_data_bill.setOnClickListener(this)
        view.button_save_bottom_sheet_dialog_fragment_data_bill.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        view?.id.let {
            when (it) {
                R.id.button_close_bottom_sheet_dialog_fragment_data_bill -> {
                    dismiss()
                }
                R.id.button_save_bottom_sheet_dialog_fragment_data_bill -> {
                    // TODO: do something in here (pending)
                    presenter.onSaveDataBill(billData)
                }
                else -> {
                    /** nothing to do in here */
                }
            }
        }
    }

    private fun doLoadData(view: View) {
        val bundle = arguments
        billData = bundle.getParcelable<Data>("dataBill")
        view.let {
            it.text_view_value_customer_number_bottom_sheet_dialog_fragment_data_bill.text = billData.customerId
            it.text_view_value_customer_name_bottom_sheet_dialog_fragment_data_bill.text = billData.customerName
            it.text_view_value_bill_period_bottom_sheet_dialog_fragment_data_bill.text = billData.period

            val decimalFormat = DecimalFormat("#,###,###")
            val billAmount = "Rp. ${decimalFormat.format(billData.amountTheBill)}"
            it.text_view_value_bill_bottom_sheet_dialog_fragment_data_bill.text = billAmount

            val totalBill = "Rp. ${decimalFormat.format(billData.amountPaid)}"
            it.text_view_value_total_bill_bottom_sheet_dialog_fragment_data_bill.text = totalBill
        }
    }

    override fun saveDataBill(isUpdated: Boolean) {
        val message = isUpdated.let {
            when (it) {
                true -> {
                    getString(R.string.bill_info_has_been_updated)
                }
                else -> {
                    getString(R.string.bill_info_has_been_saved)
                }
            }
        }
        showSnackbar(message = message, duration = Snackbar.LENGTH_LONG)
        dismiss()
    }

    override fun saveDataBillFailed(message: String) {
        showSnackbar(message = message, duration = Snackbar.LENGTH_LONG)
    }

    fun BottomSheetDialogFragment.showSnackbar(message: CharSequence, duration: Int) {
        Snackbar.make(activity.findViewById(android.R.id.content), message, duration)
                .show()
    }

}