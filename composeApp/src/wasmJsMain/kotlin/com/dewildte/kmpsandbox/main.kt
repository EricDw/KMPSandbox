package com.dewildte.kmpsandbox

import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.dewildte.kmpsandbox.app.AppController
import com.dewildte.kmpsandbox.app.ui.AppScreen
import com.dewildte.kmpsandbox.dtos.AppConfigDto
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        val scope = rememberCoroutineScope()
        val actor = remember(scope) {
            AppController(
                scope = scope,
                fetchAppConfig = {
                    AppConfigDto(
                        greeting = "WASM"
                    )
                }
            )
        }
        AppScreen(
            appActor = actor
        )
    }
}