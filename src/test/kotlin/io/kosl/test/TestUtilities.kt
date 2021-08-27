package io.kosl.test

import io.kosl.tool.KoslCommand

val enableTestKoslDryRun = System.getenv("KOSL_TEST_DRY_RUN") != "false"

fun <T> cleanListOf(vararg items: T): List<T> =
  listOf(*items).filter { it.toString().isNotEmpty() }

fun runKoslCommand(vararg args: String) {
  val command = KoslCommand()
  command.parse(cleanListOf(*args))
  command.run()
}
