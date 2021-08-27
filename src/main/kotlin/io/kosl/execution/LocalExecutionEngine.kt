package io.kosl.execution

import java.nio.file.Path
import kotlin.system.exitProcess

class LocalExecutionEngine: ExecutionEngine {
  override fun execute(command: List<ExecutionParameter>, localContextDirectory: Path) {
    val args = command.map { it.toCommandArgument(localContextDirectory) }
    val builder = ProcessBuilder(args)
      .directory(localContextDirectory.toFile())
      .inheritIO()

    val process = builder.start()
    val exitCode = process.waitFor()

    if (exitCode != 0) {
      exitProcess(exitCode)
    }
  }
}
