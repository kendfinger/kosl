package io.kosl.tool

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.enum
import io.kosl.build.BuildEngines
import io.kosl.spec.ServiceSpec
import io.kosl.spec.WorkspaceSpec
import io.kosl.spec.render
import io.kosl.state.ServiceState
import java.nio.file.Paths

class KoslCommand: CliktCommand() {
  val buildEngine by option("-b", "--build-engine")
    .enum<BuildEngines> { it.id }
    .default(BuildEngines.DockerBuild)

  override fun run() {
    val workspaceSpec = WorkspaceSpec.loadFromPath("kosl-workspace.json")
    val workspace = workspaceSpec.render()

    for (service in workspace.services) {
      buildEngine.engine.build(service.createBuildJob())
    }
  }
}
