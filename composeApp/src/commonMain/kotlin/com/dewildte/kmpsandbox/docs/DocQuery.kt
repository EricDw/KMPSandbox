package com.dewildte.kmpsandbox.docs

interface DocQuery {
    suspend fun execute(currentDoc: Doc)
}