package io.kosl.build

import io.kosl.util.executeInteractiveProcess
import kotlin.io.path.absolutePathString

class BuildahEngine: BuildEngine {
  override fun process(job: BuildEngineJob) {
    val buildCommand = mutableListOf(
      "buildah",
      "bud",
      "-t",
      job.targetImageName,
      "-f",
      job.buildFilePath.absolutePathString(),
      job.contextDirectoryPath.absolutePathString()
    )

    executeInteractiveProcess(buildCommand)

    if (job.push) {
      val pushCommand = mutableListOf(
        "buildah",
        "push",
        job.targetImageName
      )

      executeInteractiveProcess(pushCommand)
    }
  }
}
