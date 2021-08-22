package io.kosl.spec

import io.kosl.state.ServiceState
import io.kosl.state.WorkspaceState
import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer
import java.nio.file.Paths

@Serializable
class WorkspaceSpec(
  val defaultImagePrefix: String,
  val services: List<String>
) {
  companion object : KoslSpec<WorkspaceSpec>(serializer())
}

fun WorkspaceSpec.render(): WorkspaceState {
  val serviceStates = mutableListOf<ServiceState>()
  for (serviceName in services) {
    val serviceDirectoryPath = Paths.get(serviceName)
    val serviceDescriptionPath = serviceDirectoryPath.resolve("kosl-service.json")
    val service = ServiceSpec.loadFromPath(serviceDescriptionPath)
    val serviceState = ServiceState(this, service, serviceDirectoryPath)
    serviceStates.add(serviceState)
  }
  return WorkspaceState(this, serviceStates)
}
