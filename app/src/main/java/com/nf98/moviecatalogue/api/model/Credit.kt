package com.nf98.moviecatalogue.api.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Credit (
    @SerializedName("cast_id")
    @Expose
    var castId: Int = 0,

    @SerializedName("character")
    @Expose
    var character: String? = null,

    @SerializedName("credit_id")
    @Expose
    var creditId: String? = null,

    @SerializedName("department")
    @Expose
    var department: String? = null,

    @SerializedName("job")
    @Expose
    var job: String? = null,

    @SerializedName("gender")
    @Expose
    var gender: Int = 0,

    @SerializedName("id")
    @Expose
    var id: Int = 0,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("profile_path")
    @Expose
    var profilePath: String? = null
) : Parcelable