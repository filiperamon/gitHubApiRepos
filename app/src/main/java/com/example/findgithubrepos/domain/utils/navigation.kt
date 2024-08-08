package com.example.findgithubrepos.domain.utils

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

fun Fragment.navigateTo(direction: NavDirections) {
    try {
      findNavController().navigate(direction)
    } catch (e: Exception) {

    }
}