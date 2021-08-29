package io.kosl.execution.engines

import io.kosl.execution.ExecutionEngine
import io.kosl.execution.ExecutionJob
import kotlin.io.path.absolute
import kotlin.system.exitProcess

class LocalExecutionEngine: ExecutionEngine {
  override fun execute(job: ExecutionJob) {
    val builder = ProcessBuilder(job.expandCommandArguments())
      .directory(job.localContextDirectory.absolute().toFile())
      .inheritIO()

    val process = builder.start()
    val exitCode = process.waitFor()

    if (exitCode != 0) {
      exitProcess(exitCode)
    }
  }
}
