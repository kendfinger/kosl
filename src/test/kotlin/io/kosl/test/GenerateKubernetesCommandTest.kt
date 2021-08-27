package io.kosl.test

import kotlin.io.path.absolutePathString
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class GenerateKubernetesCommandTest {
  lateinit var environment: KoslTestEnvironment

  @BeforeTest
  fun before() {
    environment = createHelloWorldEnvironment()
  }

  @Test
  fun `generates-kubernetes command succeeds`() = runKoslCommand(
    "-w",
    environment.workspaceDirectoryPath.absolutePathString(),
    "--dry-run",
    "generate-kubernetes"
  )

  @AfterTest
  fun after() {
    environment.destroy()
  }
}
