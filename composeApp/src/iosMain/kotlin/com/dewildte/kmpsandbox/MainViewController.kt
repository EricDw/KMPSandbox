package com.dewildte.kmpsandbox

import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.ComposeUIViewController
import com.dewildte.kmpsandbox.dtos.AppConfigDto
import platform.UIKit.UIDevice

fun MainViewController() = ComposeUIViewController {
    val scope = rememberCoroutineScope()
    val actor = remember(scope) {
        AppActor(
            scope = scope,
            fetchAppConfig = {
                AppConfigDto(
                    greeting = UIDevice.currentDevice.systemName
                )
            }
        )
    }
    AppScreen(
        appActor = actor,
    )
}