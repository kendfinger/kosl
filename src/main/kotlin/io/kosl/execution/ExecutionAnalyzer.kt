package io.kosl.execution

import java.nio.file.Path

class ExecutionAnalyzer(val command: List<ExecutionParameter>) {
  private val expanded = command.expand()

  fun analyze(): ExecutionAnalysis = ExecutionAnalysis(
    findRequiredPaths(),
    findRequiredCommands(),
    findSubCommandPatterns()
  )

  fun findRequiredPaths(): List<Path> {
    return expanded.filterIsInstance<RelativePath>().map { it.path }
  }

  fun findRequiredCommands(): List<String> {
    val commands = mutableListOf<String>()

    for (parameter in expanded) {
      if (parameter is CommandName) {
        commands.add(parameter.command)
      }
    }

    return commands
  }

  fun findSubCommandPatterns(): List<List<String>> {
    val patterns = mutableListOf<List<String>>()
    val seen = mutableListOf<String>()

    for (parameter in expanded) {
      if (parameter is CommandName) {
        val commandName = parameter.command
        seen.clear()
        seen.add(commandName)
      }

      if (parameter is SubCommandName) {
        val commandName = parameter.subcommand
        seen.add(commandName)
        patterns.add(seen.toMutableList())
      }
    }
    return patterns
  }
}
