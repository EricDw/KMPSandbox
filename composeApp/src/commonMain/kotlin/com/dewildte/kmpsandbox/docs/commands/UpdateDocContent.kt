package com.dewildte.kmpsandbox.docs.commands

import com.dewildte.kmpsandbox.docs.Doc
import com.dewildte.kmpsandbox.docs.DocCommand

class UpdateDocContent(
    private val newContent: String
) : DocCommand {
    override suspend fun execute(currentDoc: Doc): Doc {
        return currentDoc.copy(
            content = newContent
        )
    }
}