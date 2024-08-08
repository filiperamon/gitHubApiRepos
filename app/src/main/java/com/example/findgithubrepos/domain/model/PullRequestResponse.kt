package com.example.findgithubrepos.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

//Cada item da lista deve exibir Nome / Foto do autor do PR,
//
// Título do PR, Data do PR e Body do PR

@Parcelize
data class PullRequestResponse(
    @SerializedName("url")
    val url: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("body")
    val body: String?,

) : Parcelable
