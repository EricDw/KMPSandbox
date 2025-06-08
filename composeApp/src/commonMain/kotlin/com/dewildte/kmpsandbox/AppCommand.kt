package com.dewildte.kmpsandbox

sealed class AppCommand {
    data object Start : AppCommand()
}
