package io.kosl.state

import io.kosl.context.KoslContext
import io.kosl.spec.WorkspaceSpec

class WorkspaceState(
  val context: KoslContext,
  val spec: WorkspaceSpec,
  val services: List<ServiceState>,
  val imagePrefix: String
)
