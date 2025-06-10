package com.dewildte.kmpsandbox.docs

data class Doc(
    val name: DocName,
    val content: String = "",
)

data class DocName(
    val value: String
)