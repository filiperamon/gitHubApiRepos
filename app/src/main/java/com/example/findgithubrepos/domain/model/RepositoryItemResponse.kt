package com.example.findgithubrepos.domain.model

import com.google.gson.annotations.SerializedName

data class RepositoryItemResponse(
    val id: Long,
    @SerializedName("node_id")
    val nodeId: String,
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    val private: Boolean,
)
