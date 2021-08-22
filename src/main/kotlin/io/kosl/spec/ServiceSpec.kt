package io.kosl.spec

import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer

@Serializable
class ServiceSpec(
  val build: BuildSpec
) {
  companion object : KoslSpec<ServiceSpec>(serializer())
}
