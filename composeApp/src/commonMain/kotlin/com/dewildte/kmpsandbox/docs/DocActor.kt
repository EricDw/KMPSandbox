package com.dewildte.kmpsandbox.docs

import com.dewildte.kmpsandbox.actor.Actor

class DocActor(
    private val parent: Actor? = null,
) : Actor {
    override fun invoke(message: Any) {
    }
}