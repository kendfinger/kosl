package io.kosl.spec

import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer

@Serializable
class WorkspaceSpec(
  val defaultImagePrefix: String,
  val services: List<String>
) {
  companion object : KoslSpec<WorkspaceSpec>(serializer())
}
