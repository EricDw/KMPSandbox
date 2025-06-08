package com.dewildte.kmpsandbox.actor

fun interface Actor {
    operator fun invoke(message: Any)
}