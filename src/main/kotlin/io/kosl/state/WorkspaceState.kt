package io.kosl.state

import io.kosl.spec.WorkspaceSpec

class WorkspaceState(
  val spec: WorkspaceSpec,
  val services: List<ServiceState>
)
