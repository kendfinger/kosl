package io.kosl.config

import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer

@Serializable
class BuildDescription(
  val file: String = "Dockerfile",
  val image: String
) {
  companion object : KoslModel<BuildDescription>(serializer())
}
