package com.dewildte.kmpsandbox

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kmpsandbox.composeapp.generated.resources.Res
import kmpsandbox.composeapp.generated.resources.compose_multiplatform
import kmpsandbox.composeapp.generated.resources.label_boot
import kmpsandbox.composeapp.generated.resources.label_button
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AppScreen(
    appActor: AppActor? = null,
) {
    val appViewModel = viewModel { AppViewModel(appActor = appActor) }
    val state by appViewModel.state.collectAsStateWithLifecycle()
    var showContent by remember() {
        mutableStateOf(false)
    }
    AppContent(
        showContent = showContent,
        enableButton = state.started,
        greeting = state.greeting,
        onShowContentChange = { showContent = !showContent },
        onBootApp = { appViewModel(AppCommand.Start) }
    )
}

@Composable
fun AppContent(
    modifier: Modifier = Modifier,
    showContent: Boolean = false,
    enableButton: Boolean = false,
    greeting: String = "",
    onShowContentChange: () -> Unit = {},
    onBootApp: () -> Unit = {}
) {
    MaterialTheme {
        Scaffold { innerPadding ->
            Column(
                modifier = modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                if (enableButton) {
                    Button(
                        onClick = onShowContentChange,
                    ) {
                        Text(stringResource(Res.string.label_button))
                    }
                } else {
                    Button(
                        onClick = onBootApp,
                    ) {
                        Text(stringResource(Res.string.label_boot))
                    }
                }

                AnimatedVisibility(showContent) {
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(painterResource(Res.drawable.compose_multiplatform), null)
                        Text(greeting)
                    }
                }
            }
        }
    }
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