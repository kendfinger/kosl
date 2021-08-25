package io.kosl.test

import io.kosl.spec.BuildSpec
import io.kosl.spec.DeploymentSpec
import io.kosl.spec.ServiceSpec
import io.kosl.spec.WorkspaceSpec
import io.kosl.tool.KoslCommand
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
  fun `generates-kubernetes command succeeds`() {
    val root = KoslCommand()
    root.parse(listOf(
      "-w",
      environment.workspaceDirectoryPath.absolutePathString(),
      "--dry-run",
      "generate-kubernetes"
    ))

    root.run()
  }

  @AfterTest
  fun after() {
    environment.destroy()
  }
}
