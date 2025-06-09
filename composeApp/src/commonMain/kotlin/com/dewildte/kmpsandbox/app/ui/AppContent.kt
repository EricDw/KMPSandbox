package com.dewildte.kmpsandbox.app.ui

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kmpsandbox.composeapp.generated.resources.Res
import kmpsandbox.composeapp.generated.resources.compose_multiplatform
import kmpsandbox.composeapp.generated.resources.label_boot
import kmpsandbox.composeapp.generated.resources.label_button
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

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
