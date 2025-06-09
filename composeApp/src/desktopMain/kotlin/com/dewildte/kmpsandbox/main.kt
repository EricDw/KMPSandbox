package com.dewildte.kmpsandbox

import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.dewildte.kmpsandbox.app.AppController
import com.dewildte.kmpsandbox.app.ui.AppScreen
import com.dewildte.kmpsandbox.dtos.AppConfigDto
import kmpsandbox.composeapp.generated.resources.Res
import kmpsandbox.composeapp.generated.resources.title_app_name
import org.jetbrains.compose.resources.stringResource

fun main() = application {

    val scope = rememberCoroutineScope()

    val actor = remember(scope) {
        AppController(
            scope = scope,
            fetchAppConfig = {
                AppConfigDto(
                    greeting = "💻 running Java ${System.getProperty("java.version")}"
                )
            }
        )
    }
    Window(
        onCloseRequest = ::exitApplication,
        title = stringResource(Res.string.title_app_name),
    ) {
        AppScreen(
            appActor = actor
        )
    }
}