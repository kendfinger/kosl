package io.kosl.execution

import java.nio.file.Path

class JoinedParameter(val left: ExecutionParameter, val right: ExecutionParameter): ExecutionParameter {
  override fun toCommandArgument(localContextDirectory: Path): String =
    "${left.toCommandArgument(localContextDirectory)}${right.toCommandArgument(localContextDirectory)}"
}
