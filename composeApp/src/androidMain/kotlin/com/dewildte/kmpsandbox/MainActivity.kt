package com.dewildte.kmpsandbox

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import com.dewildte.kmpsandbox.dtos.AppConfigDto

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val scope = rememberCoroutineScope()
            val actor = remember(scope) {
                AppActor(
                    scope = scope,
                    fetchAppConfig = {
                        AppConfigDto(
                            greeting = "Android ${Build.VERSION.SDK_INT}"
                        )
                    }
                )
            }
            AppScreen(
                appActor = actor
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    AppScreen()
}