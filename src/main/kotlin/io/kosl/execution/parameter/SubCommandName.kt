package io.kosl.execution.parameter

import io.kosl.io.Path

class SubCommandName(val subcommand: String): ExecutionParameter() {
  override fun toCommandArgument(localContextDirectory: Path): String = subcommand
}
