package com.dewildte.kmpsandbox

import com.dewildte.kmpsandbox.actor.Behavior
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppBehavior(
) : Behavior<AppContext> {
    override fun invoke(
        context: AppContext,
        message: Any
    ) {
        when (message as AppCommand) {
            AppCommand.Start -> {
                context.scope.launch {
                    context.config = context.fetchAppConfig()
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