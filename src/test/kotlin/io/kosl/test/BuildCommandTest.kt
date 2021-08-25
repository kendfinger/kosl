package io.kosl.test

import io.kosl.tool.KoslCommand
import kotlin.io.path.absolutePathString
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class BuildCommandTest {
  lateinit var environment: KoslTestEnvironment

  @BeforeTest
  fun before() {
    environment = createHelloWorldEnvironment()
  }

  @Test
  fun `build command succeeds with buildah engine`() {
    val root = KoslCommand()
    root.parse(cleanListOf(
      "-w",
      environment.workspaceDirectoryPath.absolutePathString(),
      if (enableTestKoslDryRun) "--dry-run" else "",
      "--build-engine",
      "buildah",
      "build",
      if (!enableTestKoslDryRun) "--push" else ""
    ))

    root.run()
  }

  @Test
  fun `build command succeeds with buildkit engine`() {
    val root = KoslCommand()
    root.parse(cleanListOf(
      "-w",
      environment.workspaceDirectoryPath.absolutePathString(),
      if (enableTestKoslDryRun) "--dry-run" else "",
      "--build-engine",
      "buildkit",
      "build",
      if (!enableTestKoslDryRun) "--push" else ""
    ))

    root.run()
  }

  @Test
  fun `build command succeeds with docker-build engine`() {
    val root = KoslCommand()
    root.parse(cleanListOf(
      "-w",
      environment.workspaceDirectoryPath.absolutePathString(),
      if (enableTestKoslDryRun) "--dry-run" else "",
      "--build-engine",
      "docker-build",
      "build",
      if (!enableTestKoslDryRun) "--push" else ""
    ))

    root.run()
  }

  @Test
  fun `build command succeeds with docker-buildx engine`() {
    val root = KoslCommand()
    root.parse(cleanListOf(
      "-w",
      environment.workspaceDirectoryPath.absolutePathString(),
      if (enableTestKoslDryRun) "--dry-run" else "",
      "--build-engine",
      "docker-buildx",
      "build",
      if (!enableTestKoslDryRun) "--push" else ""
    ))

    root.run()
  }

  @AfterTest
  fun after() {
    environment.destroy()
  }
}
