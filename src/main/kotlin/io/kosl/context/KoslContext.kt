package io.kosl.context

import io.kosl.build.BuildEngine
import io.kosl.execution.ExecutionAnalyzer
import io.kosl.execution.ExecutionEngine
import io.kosl.execution.ExecutionParameter
import io.kosl.state.WorkspaceState
import java.nio.file.Path
import kotlin.io.path.pathString

class KoslContext {
  lateinit var workspaceDirectoryPath: Path
  lateinit var workspaceState: WorkspaceState
  lateinit var buildEngine: BuildEngine
  lateinit var tag: String
  lateinit var executionEngine: ExecutionEngine

  var overrideImagePrefix: String? = null
  var isDryRun: Boolean = false
  var shouldAnalyzeExecution: Boolean = false

  fun logCommandExecution(command: List<ExecutionParameter>, ran: CommandRunState = CommandRunState.Automatic) {
    val prefix = when (ran) {
      CommandRunState.Automatic -> if (isDryRun) {
        "(dry) "
      } else {
        ""
      }

      CommandRunState.DryRun -> "(dry) "
      CommandRunState.Executed -> ""
    }

    println("${prefix}$ ${command.joinToString(" ") { it.toCommandArgument(workspaceDirectoryPath) }}")
  }

  fun executeInteractiveProcess(command: List<ExecutionParameter>, run: CommandRunState = CommandRunState.Automatic) {
    logCommandExecution(command, ran = run)
    if ((run == CommandRunState.Automatic && isDryRun) || run == CommandRunState.DryRun) {
      return
    }

    if (shouldAnalyzeExecution) {
      val execution = ExecutionAnalyzer(command).analyze()
      execution.requiredFilePaths.forEach {
        println("Execution Requires Path: ${it.pathString}")
      }

      execution.requiredCommandNames.forEach {
        println("Execution Requires Command: $it")
      }

      execution.requiredSubCommandPatterns.forEach {
        println("Execution Requires Subcommand: ${it.joinToString(" ")}")
      }
    }

    executionEngine.execute(command, workspaceDirectoryPath)
  }

  enum class CommandRunState {
    Automatic,
    Executed,
    DryRun
  }
}
