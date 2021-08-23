package io.kosl.state

import io.kosl.spec.WorkspaceSpec
import java.nio.file.Path

class WorkspaceState(
  val spec: WorkspaceSpec,
  val workspaceDirectoryPath: Path,
  val services: List<ServiceState>,
  val imagePrefix: String
)
