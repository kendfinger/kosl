package io.kosl.spec

import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer

@Serializable
class DeploymentSpec(
  val replicas: Int
) {
  companion object : KoslSpec<DeploymentSpec>(serializer())
}
