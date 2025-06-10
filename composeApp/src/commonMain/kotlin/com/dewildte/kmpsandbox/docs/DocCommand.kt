package com.dewildte.kmpsandbox.docs

interface DocCommand {
    suspend fun execute(currentDoc: Doc): Doc
}