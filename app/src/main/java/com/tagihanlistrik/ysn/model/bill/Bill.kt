package com.tagihanlistrik.ysn.model.bill
import com.google.gson.annotations.SerializedName


/**
 * Created by yudisetiawan on 1/7/18.
 */
data class Bill(
		@SerializedName("error") val error: Boolean,
		@SerializedName("message") val message: String,
		@SerializedName("data") val data: Data
)

data class Data(
		@SerializedName("no_pelanggan") val noPelanggan: String,
		@SerializedName("product_name") val productName: String,
		@SerializedName("jumlah_tagihan") val jumlahTagihan: Int,
		@SerializedName("jumlah_bayar") val jumlahBayar: Int,
		@SerializedName("admin") val admin: Int,
		@SerializedName("terbayar") val terbayar: Int,
		@SerializedName("nama") val nama: String,
		@SerializedName("periode") val periode: String,
		@SerializedName("tagihan_id") val tagihanId: Int
)
