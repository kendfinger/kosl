package io.kosl.execution

import io.kosl.execution.parameter.ExecutionParameter
import io.kosl.io.Path

interface ExecutionEngine {
  fun execute(job: ExecutionJob)
}
