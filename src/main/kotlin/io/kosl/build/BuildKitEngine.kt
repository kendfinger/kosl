package io.kosl.build

import io.kosl.context.KoslContext
import kotlin.io.path.absolutePathString
import kotlin.io.path.relativeTo

class BuildKitEngine: BuildEngine {
  override fun process(context: KoslContext, job: BuildEngineJob) {
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
      "type=image,name=${job.targetImageName}:${job.targetImageTag},push=${job.push}"
    )

    context.executeInteractiveProcess(command)
  }
}
