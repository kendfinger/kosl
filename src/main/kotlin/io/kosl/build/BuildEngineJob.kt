package io.kosl.build

import io.kosl.io.Path

class BuildEngineJob(
  val contextDirectoryPath: Path,
  val buildFilePath: Path,
  val targetImageName: String,
  val targetImageTag: String,
  val push: Boolean
)
