package com.alamin.placeholder.model.data
import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue


@Entity
@Parcelize
data class User(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("address")
    @Embedded
    val address: @RawValue Address,
    @SerializedName("company")
    @Embedded()
    val company: @RawValue Company,
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("website")
    val website: String
): Parcelable

data class Address(
    @SerializedName("city")
    val city: String,
    @SerializedName("geo")
    @Embedded
    val geo: @RawValue Geo,
    @SerializedName("street")
    val street: String,
    @SerializedName("suite")
    val suite: String,
    @SerializedName("zipcode")
    val zipcode: String
)

data class Company(
    @SerializedName("bs")
    val bs: String,
    @SerializedName("catchPhrase")
    val catchPhrase: String,
    @SerializedName("name")
    val companyName: String
)

data class Geo(
    @SerializedName("lat")
    val lat: String,
    @SerializedName("lng")
    val lng: String
)