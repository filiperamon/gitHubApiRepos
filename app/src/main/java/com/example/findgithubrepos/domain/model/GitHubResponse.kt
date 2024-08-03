package com.example.findgithubrepos.domain.model

import com.google.gson.annotations.SerializedName

data class GitHubResponse(
    @SerializedName("total_count")
    val totalCount: Long,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("item")
    val items: List<RepositoryItemResponse>
)
