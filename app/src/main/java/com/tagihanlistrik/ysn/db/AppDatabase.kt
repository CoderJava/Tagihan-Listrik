package com.tagihanlistrik.ysn.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.tagihanlistrik.ysn.db.dao.BillLocalDao
import com.tagihanlistrik.ysn.db.entity.BillLocal

/**
 * Created by yudisetiawan on 1/12/18.
 */

@Database(entities = arrayOf(BillLocal::class), version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun billLocalDao(): BillLocalDao

}