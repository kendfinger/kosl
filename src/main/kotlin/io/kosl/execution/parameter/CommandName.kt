package io.kosl.execution.parameter

import io.kosl.io.Path

class CommandName(val command: String): ExecutionParameter() {
  override fun toCommandArgument(localContextDirectory: Path): String = command
}
