@file:OptIn(ExperimentalCoroutinesApi::class)

package com.dewildte.kmpsandbox.docs

import com.dewildte.kmpsandbox.docs.commands.UpdateDocContent
import com.dewildte.kmpsandbox.docs.queries.GetDocName
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DocActorTest {

    @Test
    fun docActor_executes_commands_and_queries() = runTest {
        // Arrange
        val initialDoc = Doc(
            name = DocName("2025/06/10"),
        )

        var actual: DocName?

        val expected = initialDoc.name

        val actor = DocActor(
            initialDoc = initialDoc,
            logger = ::println
        )

        val command = UpdateDocContent("New content")
        val query = GetDocName()

        // Act
        actor(command)
        actor(query)
        actual = query.result.await()

        // Assert
        assertEquals(expected, actual)
    }
}
