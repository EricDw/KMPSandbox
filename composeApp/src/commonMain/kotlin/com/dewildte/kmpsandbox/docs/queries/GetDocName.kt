package com.dewildte.kmpsandbox.docs.queries

import com.dewildte.kmpsandbox.docs.Doc
import com.dewildte.kmpsandbox.docs.DocName
import com.dewildte.kmpsandbox.docs.DocQuery
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred

class GetDocName : DocQuery {
    private val _result = CompletableDeferred<DocName>()
    val result: Deferred<DocName> = _result
    override suspend fun execute(currentDoc: Doc) {
        _result.complete(currentDoc.name)
    }
}