package io.kosl.execution

import java.nio.file.Path

class RawArgument(val argument: String): ExecutionParameter() {
  override fun toCommandArgument(localContextDirectory: Path): String = argument
}
