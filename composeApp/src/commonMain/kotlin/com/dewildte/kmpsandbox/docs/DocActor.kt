@file:OptIn(ExperimentalCoroutinesApi::class)

package com.dewildte.kmpsandbox.docs

import com.dewildte.kmpsandbox.actor.Actor
import com.dewildte.kmpsandbox.actor.ActorMessage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.whileSelect

class DocActor(
    initialDoc: Doc,
    private val parent: Actor? = null,
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val logger: (message: String) -> Unit = {},
) : Actor {

    private val tag = "DocActor"

    private val scope: CoroutineScope = CoroutineScope(
        dispatcher
    )

    private val systemMailbox = Channel<ActorMessage>(
        capacity = Channel.UNLIMITED
    )

    private val userMailbox = Channel<Any>(
        capacity = Channel.UNLIMITED
    )

    private val messageHandler = scope.launch {
        whileSelect {
            systemMailbox.onReceive { message ->
                handleSystemMessage(message)
            }

            userMailbox.onReceive { message ->
                return@onReceive when (message) {
                    is DocCommand -> executeCommand(message)
                    is DocQuery -> executeQuery(message)
                    else -> false
                }
            }
        }
    }

    private var currentDoc: Doc = initialDoc

    override fun invoke(
        message: Any
    ) {
        scope.launch {
            when (message) {
                is DocCommand -> userMailbox.send(message)
                is DocQuery -> userMailbox.send(message)
                is ActorMessage -> systemMailbox.send(message)
            }
        }
    }

    private suspend fun executeCommand(
        command: DocCommand
    ): Boolean {
        logger("$tag executing command: $command")
        currentDoc = command.execute(currentDoc)
        return true
    }

    private suspend fun executeQuery(
        query: DocQuery
    ): Boolean {
        logger("$tag executing query: $query")
        query.execute(currentDoc)
        return true
    }

    private suspend fun handleSystemMessage(
        message: ActorMessage
    ): Boolean {
        logger("$tag handling system message: ${message::class.simpleName}")
        return when (message) {
            is ActorMessage.Stop -> {
                // Clean up if needed
                // Return false to stop the whileSelect loop
                false
            }
        }
    }

}