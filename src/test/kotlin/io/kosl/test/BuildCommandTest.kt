package io.kosl.test

import kotlin.io.path.absolutePathString
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class BuildCommandTest {
  lateinit var environment: KoslTestWorkspace

  @BeforeTest
  fun before() {
    environment = createHelloWorldEnvironment()
  }

  @Test
  fun `build command succeeds with buildah engine`() = runKoslCommand(
    "-w",
    environment.workspaceDirectoryPath.absolutePathString(),
    if (enableTestKoslDryRun) "--dry-run" else "",
    "--build-engine",
    "buildah",
    "build",
    if (!enableTestKoslDryRun) "--push" else ""
  )

  @Test
  fun `build command succeeds with buildkit engine`() = runKoslCommand(
    "-w",
    environment.workspaceDirectoryPath.absolutePathString(),
    if (enableTestKoslDryRun) "--dry-run" else "",
    "--build-engine",
    "buildkit",
    "build",
    if (!enableTestKoslDryRun) "--push" else ""
  )

  @Test
  fun `build command succeeds with docker-build engine`() = runKoslCommand(
    "-w",
    environment.workspaceDirectoryPath.absolutePathString(),
    if (enableTestKoslDryRun) "--dry-run" else "",
    "--build-engine",
    "docker-build",
    "build",
    if (!enableTestKoslDryRun) "--push" else ""
  )

  @Test
  fun `build command succeeds with docker-buildx engine`() = runKoslCommand(
    "-w",
    environment.workspaceDirectoryPath.absolutePathString(),
    if (enableTestKoslDryRun) "--dry-run" else "",
    "--build-engine",
    "docker-buildx",
    "build",
    if (!enableTestKoslDryRun) "--push" else ""
  )

  @AfterTest
  fun after() {
    environment.destroy()
  }
}
