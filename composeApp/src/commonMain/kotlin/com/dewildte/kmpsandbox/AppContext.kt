package com.dewildte.kmpsandbox

import com.dewildte.kmpsandbox.actor.ActorContext
import com.dewildte.kmpsandbox.actor.Behavior
import com.dewildte.kmpsandbox.dtos.AppConfigDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow

class AppContext(
    val scope: CoroutineScope,
    val fetchAppConfig: suspend () -> AppConfigDto
) : ActorContext {

    var config: AppConfigDto? = null

    val state: MutableStateFlow<AppState> =
        MutableStateFlow(AppState())

    var behavior: Behavior<AppContext> =
        AppBehavior(
            fetchAppConfig = fetchAppConfig
        )
}