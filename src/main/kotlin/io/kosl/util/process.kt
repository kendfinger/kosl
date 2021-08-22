package io.kosl.util

import java.nio.file.Path
import kotlin.system.exitProcess

fun executeInteractiveProcess(command: List<String>, cwd: Path? = null) {
  println("$ ${command.joinToString(" ")}")

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
