package io.kosl.build

import io.kosl.util.executeInteractiveProcess
import kotlin.io.path.absolutePathString
import kotlin.system.exitProcess

class DockerBuildEngine: BuildEngine {
  override fun build(job: BuildEngineJob) {
    val command = listOf(
      "docker",
      "build",
      "-t",
      job.targetImageName,
      "-f",
      job.buildFilePath.absolutePathString(),
      job.contextDirectoryPath.absolutePathString()
    )

    executeInteractiveProcess(command)
  }
}
