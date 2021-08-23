package io.kosl.build

import io.kosl.util.executeInteractiveProcess
import kotlin.io.path.absolutePathString
import kotlin.io.path.relativeTo
import kotlin.system.exitProcess

class BuildKitEngine: BuildEngine {
  override fun process(job: BuildEngineJob) {
    val command = mutableListOf(
      "buildctl",
      "build",
      "--progress",
      "plain",
      "--frontend",
      "dockerfile.v0",
      "--local",
      "context=${job.contextDirectoryPath.absolutePathString()}",
      "--local",
      "dockerfile=${job.contextDirectoryPath.absolutePathString()}",
      "--opt",
      "source=${job.buildFilePath.relativeTo(job.contextDirectoryPath).absolutePathString()}",
      "--output",
      "type=image,name=${job.targetImageName},push=${job.push}"
    )

    executeInteractiveProcess(command)
  }
}
