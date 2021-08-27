package io.kosl.execution

import java.nio.file.Path

interface ExecutionEngine {
  fun execute(command: List<ExecutionParameter>, localContextDirectory: Path)
}
