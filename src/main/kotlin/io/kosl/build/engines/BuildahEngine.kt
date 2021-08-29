package io.kosl.build.engines

import io.kosl.build.BuildEngine
import io.kosl.build.BuildEngineJob
import io.kosl.context.KoslContext
import io.kosl.execution.parameter.CommandName
import io.kosl.execution.parameter.RawArgument
import io.kosl.execution.parameter.RelativePath
import io.kosl.execution.parameter.SubCommandName

class BuildahEngine: BuildEngine {
  override fun process(context: KoslContext, job: BuildEngineJob) {
    val buildCommand = mutableListOf(
      CommandName("buildah"),
      SubCommandName("bud"),
      RawArgument("-t"),
      RawArgument("${job.targetImageName}:${job.targetImageTag}"),
      RawArgument("-f"),
      RelativePath(job.buildFilePath),
      RelativePath(job.contextDirectoryPath)
    )

    context.executeInteractiveProcess(buildCommand)

    if (job.push) {
      val pushCommand = mutableListOf(
        CommandName("buildah"),
        SubCommandName("push"),
        RawArgument("${job.targetImageName}:${job.targetImageTag}")
      )

      context.executeInteractiveProcess(pushCommand)
    }
  }
}
