package com.dewildte.kmpsandbox

import com.dewildte.kmpsandbox.actor.Behavior
import com.dewildte.kmpsandbox.dtos.AppConfigDto
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppBehavior(
    private val fetchAppConfig: suspend () -> AppConfigDto
) : Behavior<AppContext> {
    override fun invoke(
        context: AppContext,
        message: Any
    ) {
        when (message as AppCommand) {
            AppCommand.Start -> {
                context.scope.launch {
                    context.config = fetchAppConfig()
                    context.state.update { it ->
                        it.copy(
                            started = true,
                            greeting = context.config?.greeting ?: ""
                        )
                    }
                }
            }
        }
    }
}