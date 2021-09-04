package io.kosl.execution.parameter

import io.kosl.io.Path

class RawArgument(val argument: String): ExecutionParameter() {
  override fun toCommandArgument(localContextDirectory: Path): String = argument
}
