package com.dewildte.kmpsandbox.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dewildte.kmpsandbox.app.AppController
import com.dewildte.kmpsandbox.app.AppCommand
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AppScreen(
    appActor: AppController
) {
    val state by appActor.state.collectAsStateWithLifecycle()
    var showContent by remember {
        mutableStateOf(false)
    }
    AppContent(
        showContent = showContent,
        enableButton = state.started,
        greeting = state.greeting,
        onShowContentChange = { showContent = !showContent },
        onBootApp = { appActor(AppCommand.Start) }
    )
}

@Preview
@Composable
private fun DefaultAppContent() {
    AppContent()
}

@Preview
@Composable
private fun VisibleAppContent() {
    AppContent(
        showContent = true,
        greeting = "Compose preview",
    )
}