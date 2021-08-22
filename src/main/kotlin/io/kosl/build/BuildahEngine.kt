package io.kosl.build

import io.kosl.util.executeInteractiveProcess
import kotlin.io.path.absolutePathString

class BuildahEngine: BuildEngine {
  override fun build(job: BuildEngineJob) {
    val command = listOf(
      "buildah",
      "bud",
      "-t",
      job.targetImageName,
      "-f",
      job.buildFilePath.absolutePathString(),
      job.contextDirectoryPath.absolutePathString()
    )

    executeInteractiveProcess(command)
  }
}
