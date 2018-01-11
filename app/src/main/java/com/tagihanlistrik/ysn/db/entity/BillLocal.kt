package com.tagihanlistrik.ysn.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by yudisetiawan on 1/11/18.
 */

@Entity(tableName = "bill_local")
data class BillLocal(
        @ColumnInfo(name = "customer_id") var customerId: String = "",
        @ColumnInfo(name = "customer_name") var customerName: String = "",
        @ColumnInfo(name = "period") var period: String = "",
        @ColumnInfo(name = "bill") var bill: Int = 0,
        @ColumnInfo(name = "admin") var admin: Int = 0,
        @ColumnInfo(name = "paid") var paid: Int = 0
) {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    var id: Long = 0
}