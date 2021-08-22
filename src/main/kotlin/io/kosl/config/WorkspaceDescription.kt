package io.kosl.config

import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer

@Serializable
class WorkspaceDescription(
  val defaultImagePrefix: String,
  val services: List<String>
) {
  companion object : KoslModel<WorkspaceDescription>(serializer())
}
