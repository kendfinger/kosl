package io.kosl.build

import io.kosl.util.executeInteractiveProcess
import kotlin.io.path.absolutePathString

class DockerBuildEngine: BuildEngine {
  override fun process(job: BuildEngineJob) {
    val buildCommand = mutableListOf(
      "docker",
      "build",
      "-t",
      job.targetImageName,
      "-f",
      job.buildFilePath.absolutePathString(),
      job.contextDirectoryPath.absolutePathString()
    )

    executeInteractiveProcess(buildCommand)

    if (job.push) {
      val pushCommand = mutableListOf(
        "docker",
        "push",
        job.targetImageName
      )

      executeInteractiveProcess(pushCommand)
    }
  }
}
