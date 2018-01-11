package com.tagihanlistrik.ysn.model.bill
import android.os.Parcel
import android.os.Parcelable
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
) : Parcelable {
	constructor(parcel: Parcel) : this(
			parcel.readString(),
			parcel.readString(),
			parcel.readInt(),
			parcel.readInt(),
			parcel.readInt(),
			parcel.readInt(),
			parcel.readString(),
			parcel.readString(),
			parcel.readInt())

	override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

	companion object CREATOR : Parcelable.Creator<Data> {
		override fun createFromParcel(parcel: Parcel): Data {
			return Data(parcel)
		}

		override fun newArray(size: Int): Array<Data?> {
			return arrayOfNulls(size)
		}
	}
}
