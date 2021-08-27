@file:Suppress("unused")
package io.kosl.execution

enum class ExecutionEngines(val id: String, val engine: ExecutionEngine) {
  Local("local", LocalExecutionEngine())
}
