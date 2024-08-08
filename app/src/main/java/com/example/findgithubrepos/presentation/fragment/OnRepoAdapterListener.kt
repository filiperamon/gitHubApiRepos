package com.example.findgithubrepos.presentation.fragment

import com.example.findgithubrepos.domain.model.RepositoryItemResponse

interface OnRepoAdapterListener {
    fun showRepoDetails(item: RepositoryItemResponse)
}