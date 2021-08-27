package io.kosl.execution

import java.nio.file.Path

interface ExecutionParameter {
  fun toCommandArgument(localContextDirectory: Path): String
}
