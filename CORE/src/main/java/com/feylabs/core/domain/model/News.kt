package com.feylabs.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class News(
    val id:String,
    val title:String,
    val author:String,
    val content :String,
    val cover_pict_url :String,
    val created_at :String,
    var is_favorite :Boolean
) : Parcelable