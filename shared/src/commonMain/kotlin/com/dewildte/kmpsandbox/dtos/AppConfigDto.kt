package com.dewildte.kmpsandbox.dtos

import kotlinx.serialization.Serializable

@Serializable
data class AppConfigDto(
    val greeting: String = "",
)