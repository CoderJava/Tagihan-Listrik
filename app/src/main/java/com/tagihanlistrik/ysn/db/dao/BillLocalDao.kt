package com.tagihanlistrik.ysn.db.dao

import android.arch.persistence.room.*
import com.tagihanlistrik.ysn.db.entity.BillLocal

/**
 * Created by yudisetiawan on 1/11/18.
 */

@Dao
interface BillLocalDao {

    @Query("select * from bill_local")
    fun getAllBillsLocal(): List<BillLocal>

    @Query("select * from bill_local where period = :arg0")
    fun getBillLocalByPeriod(period: String): BillLocal

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBillLocal(billLocal: BillLocal)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateBillLocal(billLocal: BillLocal)

    @Delete
    fun deleteBillLocal(billLocal: BillLocal)

}