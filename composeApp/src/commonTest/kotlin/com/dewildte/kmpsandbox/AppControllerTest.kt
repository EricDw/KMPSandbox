@file:OptIn(ExperimentalCoroutinesApi::class)

package com.dewildte.kmpsandbox

import com.dewildte.kmpsandbox.app.AppCommand
import com.dewildte.kmpsandbox.app.AppController
import com.dewildte.kmpsandbox.dtos.AppConfigDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AppControllerTest {

    @Test
    fun appController_starts() = runTest {
        // Arrange
        var configFetched = false
        var documentsFetched = false
        val controller = AppController(
            scope = CoroutineScope(UnconfinedTestDispatcher()),
            fetchAppConfig = {
                configFetched = true
                AppConfigDto()
            },
            fetchDocuments = {
                documentsFetched = true
                emptyList()
            }
        )

        // Act
        controller(AppCommand.Start)

        // Assert
        assertTrue(controller.state.value.started, "Controller was not started")
        assertTrue(configFetched, "AppConfig was not fetched")
        assertTrue(documentsFetched, "Documents were not fetched")
    }

    @Test
    fun appController_does_not_start_twice() = runTest {
        // Arrange
        var configFetched = false
        var documentsFetched = false
        val controller = AppController(
            scope = CoroutineScope(UnconfinedTestDispatcher()),
            fetchAppConfig = {
                configFetched = true
                AppConfigDto()
            },
            fetchDocuments = {
                documentsFetched = true
                emptyList()
            }
        )

        // Act
        controller(AppCommand.Start)

        assertTrue(controller.state.value.started, "Controller was not started")
        assertTrue(configFetched, "AppConfig was not fetched")
        assertTrue(documentsFetched, "Documents were not fetched")

        configFetched = false
        documentsFetched = false
        controller(AppCommand.Start)

        // Assert
        assertTrue(controller.state.value.started, "Controller was not started")
        assertFalse(configFetched, "AppConfig was fetched")
        assertFalse(documentsFetched, "Documents were fetched")
    }
}