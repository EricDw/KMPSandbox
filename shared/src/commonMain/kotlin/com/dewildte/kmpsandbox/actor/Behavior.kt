package com.dewildte.kmpsandbox.actor

fun interface Behavior<C: ActorContext> {
    operator fun invoke(context: C, message: Any)
}