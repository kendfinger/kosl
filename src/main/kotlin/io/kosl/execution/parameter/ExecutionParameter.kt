package io.kosl.execution.parameter

import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.absolute

abstract class ExecutionParameter {
  abstract fun toCommandArgument(localContextDirectory: Path): String

  open fun listSubParameters(): List<ExecutionParameter> = emptyList()

  override fun toString(): String {
    return "${this.javaClass.simpleName}(${toCommandArgument(Paths.get(".").absolute())})"
  }
}
