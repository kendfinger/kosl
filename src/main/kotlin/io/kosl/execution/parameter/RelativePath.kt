package io.kosl.execution.parameter

import java.nio.file.Path
import kotlin.io.path.pathString
import kotlin.io.path.relativeTo

class RelativePath(val path: Path): ExecutionParameter() {
  override fun toCommandArgument(localContextDirectory: Path): String {
    return path.relativeTo(localContextDirectory).pathString
  }
}
