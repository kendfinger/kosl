package io.kosl.execution

fun List<ExecutionParameter>.expand(): List<ExecutionParameter> {
  val all = mutableListOf<ExecutionParameter>()
  for (parameter in this) {
    all.add(parameter)
    all.addAll(parameter.listSubParameters())
  }
  return all
}
