package com.dewildte.kmpsandbox

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dewildte.kmpsandbox.actor.Actor

class AppViewModel(
    appActor: AppActor? = null
) : ViewModel(), Actor {

    val actor = appActor ?: AppActor(scope = viewModelScope)

    val state = actor.state

    override fun invoke(message: Any) = actor(message)
}