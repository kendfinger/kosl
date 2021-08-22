package io.kosl.spec

import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer

@Serializable
class BuildSpec(
  val file: String = "Dockerfile",
  val image: String
) {
  companion object : KoslSpec<BuildSpec>(serializer())
}
