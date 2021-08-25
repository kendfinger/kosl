package io.kosl.test

val enableTestKoslDryRun = System.getenv("KOSL_TEST_DRY_RUN") != "false"

fun <T> cleanListOf(vararg items: T): List<T> =
  listOf<T>(*items).filter { it.toString().isNotEmpty() }
