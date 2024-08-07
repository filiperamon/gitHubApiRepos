package com.example.findgithubrepos.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitHubResponse(
    @SerializedName("total_count")
    val totalCount: Long
): Parcelable
