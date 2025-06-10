package com.dewildte.kmpsandbox.actor

sealed interface ActorMessage {

    data object Stop : ActorMessage

}