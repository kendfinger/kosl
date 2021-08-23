package io.kosl.tool

import io.kosl.build.BuildEngine
import io.kosl.state.WorkspaceState
import java.nio.file.Path

class KoslContext {
  lateinit var workspaceDirectoryPath: Path
  lateinit var workspace: WorkspaceState
  lateinit var buildEngine: BuildEngine
}
