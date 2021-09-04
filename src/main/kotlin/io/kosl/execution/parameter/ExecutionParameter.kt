package io.kosl.execution.parameter

import io.kosl.io.Path
import kotlin.io.path.absolute

abstract class ExecutionParameter {
  abstract fun toCommandArgument(localContextDirectory: Path): String

  open fun listSubParameters(): List<ExecutionParameter> = emptyList()

  override fun toString(): String {
    return "${this.javaClass.simpleName}(${toCommandArgument(Path.of(".").absolute())})"
  }
}
