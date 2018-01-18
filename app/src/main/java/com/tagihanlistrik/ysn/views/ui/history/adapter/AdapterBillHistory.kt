package com.tagihanlistrik.ysn.views.ui.history.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tagihanlistrik.ysn.R
import com.tagihanlistrik.ysn.db.entity.BillLocal
import kotlinx.android.synthetic.main.item_bill_history.view.*
import java.text.DecimalFormat

/**
 * Created by yudisetiawan on 1/17/18.
 */
class AdapterBillHistory(private val billsLocal: List<BillLocal>) : RecyclerView.Adapter<AdapterBillHistory.ViewHolderBillHistory>() {

    private val TAG = javaClass.simpleName

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolderBillHistory =
            ViewHolderBillHistory(LayoutInflater.from(parent?.context)
                    .inflate(R.layout.item_bill_history, null))

    override fun getItemCount(): Int = billsLocal.size

    override fun onBindViewHolder(holder: ViewHolderBillHistory, position: Int) {
        holder.itemView.let {
            val billLocal = billsLocal[position]
            it.text_view_customer_name_item_bill_history.text = billLocal.customerName
            it.text_view_period_item_bill_history.text = billLocal.period

            val billTotal = "Rp ${DecimalFormat("#,###,###").format(billLocal.bill)}"
            it.text_view_total_bill_item_bill_history.text = billTotal
        }
    }

    inner class ViewHolderBillHistory(itemView: View) : RecyclerView.ViewHolder(itemView)

}