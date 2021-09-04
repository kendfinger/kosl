package io.kosl.execution.parameter

import io.kosl.io.Path

class JoinedParameter(val left: ExecutionParameter, val right: ExecutionParameter): ExecutionParameter() {
  override fun toCommandArgument(localContextDirectory: Path): String =
    "${left.toCommandArgument(localContextDirectory)}${right.toCommandArgument(localContextDirectory)}"

  override fun listSubParameters(): List<ExecutionParameter> = listOf(left, right)
}
