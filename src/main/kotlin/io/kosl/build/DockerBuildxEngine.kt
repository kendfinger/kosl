package io.kosl.build

import io.kosl.context.KoslContext
import kotlin.io.path.absolutePathString

class DockerBuildxEngine: BuildEngine {
  override fun process(context: KoslContext, job: BuildEngineJob) {
    val command = mutableListOf(
      "docker",
      "buildx",
      "build",
      "-t",
      "${job.targetImageName}:${job.targetImageTag}",
      "-f",
      job.buildFilePath.absolutePathString()
    )

    if (job.push) {
      command.add("--push")
    } else {
      command.add("--load")
    }

    command.add(job.contextDirectoryPath.absolutePathString())
    context.executeInteractiveProcess(command)
  }
}
