package io.kosl.build

import io.kosl.context.KoslContext
import kotlin.io.path.absolutePathString

class BuildahEngine: BuildEngine {
  override fun process(context: KoslContext, job: BuildEngineJob) {
    val buildCommand = mutableListOf(
      "buildah",
      "bud",
      "-t",
      "${job.targetImageName}:${job.targetImageTag}",
      "-f",
      job.buildFilePath.absolutePathString(),
      job.contextDirectoryPath.absolutePathString()
    )

    context.executeInteractiveProcess(buildCommand)

    if (job.push) {
      val pushCommand = mutableListOf(
        "buildah",
        "push",
        "${job.targetImageName}:${job.targetImageTag}"
      )

      context.executeInteractiveProcess(pushCommand)
    }
  }
}
