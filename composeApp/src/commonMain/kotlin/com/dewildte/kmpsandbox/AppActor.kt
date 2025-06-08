package com.dewildte.kmpsandbox

import com.dewildte.kmpsandbox.actor.Actor
import com.dewildte.kmpsandbox.dtos.AppConfigDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.asStateFlow

class AppActor(
    scope: CoroutineScope,
    fetchAppConfig: suspend () -> AppConfigDto = { AppConfigDto() }
) : Actor {

    private val appContext = AppContext(
        scope = scope,
        fetchAppConfig = fetchAppConfig
    )

    val state = appContext.state.asStateFlow()

    override fun invoke(message: Any) {
        appContext.behavior.invoke(appContext, message)
    }
}