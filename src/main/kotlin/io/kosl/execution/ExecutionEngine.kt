package io.kosl.execution

interface ExecutionEngine {
  fun execute(job: ExecutionJob)
}
