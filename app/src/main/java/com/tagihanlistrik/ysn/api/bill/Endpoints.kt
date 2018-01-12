package com.tagihanlistrik.ysn.api.bill

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by root on 18/09/17.
 */
interface Endpoints {

    @POST("tagihan/cek")
    fun checkTheBill(
            @Query("product") product: String = "PLN",
            @Query("phone_number") phoneNumber: String,
            @Query("nomor_rekening") customerId: String
    ): Observable<ResponseBody>

}