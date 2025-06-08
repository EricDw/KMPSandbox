package com.dewildte.kmpsandbox

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform