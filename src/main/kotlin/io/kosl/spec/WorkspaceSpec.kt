package io.kosl.spec

import io.kosl.state.ServiceState
import io.kosl.state.WorkspaceState
import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer
import java.nio.file.Path
import java.nio.file.Paths

@Serializable
class WorkspaceSpec(
  val defaultImagePrefix: String,
  val services: List<String>
) {
  companion object : KoslSpec<WorkspaceSpec>(serializer())
}
