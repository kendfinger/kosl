package io.kosl.config

import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer

@Serializable
class ServiceDescription(
  val build: BuildDescription
) {
  companion object : KoslModel<ServiceDescription>(serializer())
}
