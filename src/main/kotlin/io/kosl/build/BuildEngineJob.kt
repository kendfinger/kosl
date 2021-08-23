package io.kosl.build

import java.nio.file.Path

class BuildEngineJob(
  val contextDirectoryPath: Path,
  val buildFilePath: Path,
  val targetImageName: String,
  val push: Boolean
)
