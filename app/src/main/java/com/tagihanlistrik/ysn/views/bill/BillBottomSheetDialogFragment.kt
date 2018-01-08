package com.tagihanlistrik.ysn.views.bill

import android.app.Dialog
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialogFragment
import android.support.design.widget.CoordinatorLayout
import android.view.View
import android.widget.Button
import com.tagihanlistrik.ysn.R
import com.tagihanlistrik.ysn.model.bill.Data
import kotlinx.android.synthetic.main.bottom_sheet_dialog_fragment_data_bill.*
import kotlinx.android.synthetic.main.bottom_sheet_dialog_fragment_data_bill.view.*
import java.text.DecimalFormat

/**
 * Created by yudisetiawan on 1/7/18.
 */
class BillBottomSheetDialogFragment : BottomSheetDialogFragment(),
        BillBottomSheetDialogFragmentView,
        View.OnClickListener {

    private val TAG = javaClass.simpleName
    private var billBottomSheetDialogFragmentPresenter: BillBottomSheetDialogFragmentPresenter? = null

    override fun setupDialog(dialog: Dialog?, style: Int) {
        var view = View.inflate(context, R.layout.bottom_sheet_dialog_fragment_data_bill, null)
        initPresenter()
        onAttachMvp()
        dialog?.setContentView(view)
        initViews(view)
        initListeners(view)
        doLoadData(view)
    }

    private fun initListeners(view: View) {
        view.button_close_bottom_sheet_dialog_fragment_data_bill.setOnClickListener(this)
    }

    private fun doLoadData(view: View) {
        val bundle = arguments
        val billData = bundle.getParcelable<Data>("dataBill")
        view.let {
            it.text_view_value_customer_number_bottom_sheet_dialog_fragment_data_bill.text = billData.noPelanggan
            it.text_view_value_customer_name_bottom_sheet_dialog_fragment_data_bill.text = billData.nama
            it.text_view_value_bill_period_bottom_sheet_dialog_fragment_data_bill.text = billData.periode
            val decimalFormat = DecimalFormat("#,###,###")
            val billAmount = decimalFormat.format(billData.jumlahTagihan)
            it.text_view_value_bill_bottom_sheet_dialog_fragment_data_bill.text = "Rp. $billAmount"
            val totalBill = decimalFormat.format(billData.jumlahBayar)
            it.text_view_value_total_bill_bottom_sheet_dialog_fragment_data_bill.text = "Rp. $totalBill"
        }
    }

    private fun initViews(view: View) {
        val layoutParams = (view.parent as View).layoutParams as CoordinatorLayout.LayoutParams
        val behavior = layoutParams.behavior
        (behavior as BottomSheetBehavior).isHideable = false
        behavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                behavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    private fun initPresenter() {
        billBottomSheetDialogFragmentPresenter = BillBottomSheetDialogFragmentPresenter()
    }

    override fun onClick(view: View?) {
        view?.id.let {
            when (it) {
                R.id.button_close_bottom_sheet_dialog_fragment_data_bill -> {
                    dismiss()
                }
                else -> {
                    // todo: do something in here
                }
            }
        }
    }

    override fun onAttachMvp() {
        billBottomSheetDialogFragmentPresenter?.onAttachView(this)
    }

    override fun onDetachMvp() {
        billBottomSheetDialogFragmentPresenter?.onDetachView()
    }

}