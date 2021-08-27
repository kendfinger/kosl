package io.kosl.execution

import java.nio.file.Path

class ExecutionAnalysis(
  val requiredFilePaths: List<Path>,
  val requiredCommandNames: List<String>,
  val requiredSubCommandPatterns: List<List<String>>
)
