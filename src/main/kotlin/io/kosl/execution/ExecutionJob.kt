package io.kosl.execution

import io.kosl.execution.analysis.ExecutionAnalysis
import io.kosl.execution.analysis.ExecutionAnalyzer
import io.kosl.execution.parameter.ExecutionParameter
import io.kosl.io.Path

class ExecutionJob(
  val command: List<ExecutionParameter>,
  val localContextDirectory: Path
) {
  fun expandCommandArguments(): List<String> = command.map { parameter ->
    parameter.toCommandArgument(localContextDirectory)
  }

  fun expandSubParameters(): List<ExecutionParameter> {
    val all = mutableListOf<ExecutionParameter>()
    for (parameter in command) {
      all.add(parameter)
      all.addAll(parameter.listSubParameters())
    }
    return all
  }

  fun analyze(): ExecutionAnalysis = ExecutionAnalyzer(this).analyze()
}
