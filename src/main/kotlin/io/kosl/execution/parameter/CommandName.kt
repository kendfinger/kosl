package io.kosl.execution.parameter

import java.nio.file.Path

class CommandName(val command: String): ExecutionParameter() {
  override fun toCommandArgument(localContextDirectory: Path): String = command
}
