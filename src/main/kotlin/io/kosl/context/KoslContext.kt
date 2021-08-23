package io.kosl.context

import io.kosl.build.BuildEngine
import io.kosl.state.WorkspaceState
import java.nio.file.Path
import kotlin.system.exitProcess

class KoslContext {
  lateinit var workspaceDirectoryPath: Path
  lateinit var workspaceState: WorkspaceState
  lateinit var buildEngine: BuildEngine
  lateinit var tag: String

  var overrideImagePrefix: String? = null
  var isDryRun: Boolean = false

  fun logCommandExecution(command: List<String>, ran: CommandRunState = CommandRunState.Automatic) {
    val prefix = when (ran) {
      CommandRunState.Automatic -> if (isDryRun) {
        "(dry) "
      } else {
        ""
      }

      CommandRunState.DryRun -> "(dry) "
      CommandRunState.Executed -> ""
    }

    println("${prefix}$ ${command.joinToString(" ")}")
  }

  fun executeInteractiveProcess(command: List<String>, cwd: Path? = null, run: CommandRunState = CommandRunState.Automatic) {
    logCommandExecution(command, ran = run)
    if ((run == CommandRunState.Automatic && isDryRun) || run == CommandRunState.DryRun) {
      return
    }

    val builder = ProcessBuilder(command)
      .inheritIO()

    if (cwd != null) {
      builder.directory(cwd.toFile())
    }

    val process = builder.start()
    val exitCode = process.waitFor()

    if (exitCode != 0) {
      exitProcess(exitCode)
    }
  }

  enum class CommandRunState {
    Automatic,
    Executed,
    DryRun
  }
}
