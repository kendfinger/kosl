package io.kosl.spec

import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer

@Serializable
class ServiceSpec(
  val name: String,
  val build: BuildSpec,
  val deployment: DeploymentSpec
) {
  companion object : KoslSpec<ServiceSpec>(serializer())
}
