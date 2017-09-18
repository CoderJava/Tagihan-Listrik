package com.tagihanlistrik.ysn.api.bill

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by root on 18/09/17.
 */
interface ApiBisaTopUp {

    @GET("tagihan/cek")
    fun checkTheBill(
            @Query("product") product: String = "PLN",
            @Query("phone_number") phoneNumber: String,
            @Query("nomor_rekening") nomorRekening: String
    ): Observable<ResponseBody>

}