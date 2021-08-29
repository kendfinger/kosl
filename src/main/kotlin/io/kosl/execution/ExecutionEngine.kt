package io.kosl.execution

import io.kosl.execution.parameter.ExecutionParameter
import java.nio.file.Path

interface ExecutionEngine {
  fun execute(job: ExecutionJob)
}
