package com.dewildte.kmpsandbox.app

import com.dewildte.kmpsandbox.actor.Actor
import com.dewildte.kmpsandbox.dtos.AppConfigDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppController(
    private val scope: CoroutineScope,
    private val fetchAppConfig: suspend () -> AppConfigDto = { AppConfigDto() },
    private val fetchDocuments: suspend (path: String) -> List<String> = { emptyList() }
) : Actor {

    private var config: AppConfigDto? = null

    private val _state: MutableStateFlow<AppState> =
        MutableStateFlow(AppState())

    private val messageFlow = MutableSharedFlow<Any>(
        extraBufferCapacity = 42
    )

    private var handler: Job? = messageFlow
        .onEach(::handleMessage)
        .launchIn(scope)

    val state = _state.asStateFlow()

    override fun invoke(message: Any) {
        scope.launch {
            messageFlow.emit(message)
        }
    }

    private suspend fun handleMessage(message: Any) {
        when (message as? AppCommand) {
            AppCommand.Start -> {
                // Don't start if already started
                if (_state.value.started) {
                    return
                }
                // Fetch config
                config = fetchAppConfig()

                // Use config

                // Load preferences

                // Load documents

                val documents = fetchDocuments("/")

                // Update state
                _state.update { it ->
                    it.copy(
                        started = true,
                        greeting = config?.greeting ?: ""
                    )
                }
            }

            null -> {
                TODO("Not implemented yet")
            }
        }
    }
}