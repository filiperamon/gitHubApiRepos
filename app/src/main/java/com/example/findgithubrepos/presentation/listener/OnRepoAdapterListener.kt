package com.example.findgithubrepos.presentation.listener

import com.example.findgithubrepos.domain.model.RepositoryItemResponse

interface OnRepoAdapterListener {
    fun showRepoDetails(item: RepositoryItemResponse)
}