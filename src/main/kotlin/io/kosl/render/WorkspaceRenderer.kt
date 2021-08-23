package io.kosl.render

import io.kosl.spec.ServiceSpec
import io.kosl.spec.WorkspaceSpec
import io.kosl.state.ServiceState
import io.kosl.state.WorkspaceState
import io.kosl.tool.KoslContext

object WorkspaceRenderer {
  fun render(spec: WorkspaceSpec, context: KoslContext): WorkspaceState {
    val serviceStates = mutableListOf<ServiceState>()
    for (serviceName in spec.services) {
      val serviceDirectoryPath = context.workspaceDirectoryPath.resolve(serviceName)
      val serviceSpecPath = serviceDirectoryPath.resolve("kosl-service.json")
      val service = ServiceSpec.loadFromPath(serviceSpecPath)
      val serviceState = ServiceState(spec, service, serviceDirectoryPath)
      serviceStates.add(serviceState)
    }
    return WorkspaceState(
      spec,
      context.workspaceDirectoryPath,
      serviceStates,
      spec.defaultImagePrefix
    )
  }
}
