package com.dewildte.kmpsandbox.app

import androidx.compose.runtime.Immutable

@Immutable
data class AppState(
    val greeting: String = "",
    val started: Boolean = false,
)
