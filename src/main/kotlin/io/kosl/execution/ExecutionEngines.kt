@file:Suppress("unused")
package io.kosl.execution

import io.kosl.execution.engines.LocalExecutionEngine

enum class ExecutionEngines(val id: String, val engine: ExecutionEngine) {
  Local("local", LocalExecutionEngine())
}
