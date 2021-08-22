package io.kosl.tool

import io.kosl.build.BuildEngine
import io.kosl.state.WorkspaceState

class KoslContext {
  lateinit var workspace: WorkspaceState
  lateinit var buildEngine: BuildEngine
}
