package com.tagihanlistrik.ysn.di.module

import android.app.Application
import android.arch.persistence.room.Room
import com.tagihanlistrik.ysn.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by yudisetiawan on 1/14/18.
 */

@Module
class RoomModule {

    @Provides
    @Singleton
    fun providesAppDatabase(application: Application): AppDatabase =
            Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    "tagihan_lisrik.db"
            )
                    .allowMainThreadQueries()
                    .build()

    @Provides
    @Singleton
    fun providesBillLocalDao(database: AppDatabase) = database.billLocalDao()
}
