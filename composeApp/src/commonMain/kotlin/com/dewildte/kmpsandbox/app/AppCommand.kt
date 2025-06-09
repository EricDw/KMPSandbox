package com.dewildte.kmpsandbox.app

sealed class AppCommand {
    data object Start : AppCommand()
}
