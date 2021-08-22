package io.kosl.tool

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.enum
import io.kosl.build.BuildEngineJob
import io.kosl.build.BuildEngines
import io.kosl.config.ServiceDescription
import io.kosl.config.WorkspaceDescription
import java.nio.file.Paths

class KoslCommand: CliktCommand() {
  val buildEngine by option("-b", "--build-engine")
    .enum<BuildEngines> { it.id }
    .default(BuildEngines.DockerBuild)

  override fun run() {
    val workspace = WorkspaceDescription.loadFromPath("kosl-workspace.json")

    for (serviceName in workspace.services) {
      val serviceDirectoryPath = Paths.get(serviceName)
      val serviceDescriptionPath = serviceDirectoryPath.resolve("kosl-service.json")
      val service = ServiceDescription.loadFromPath(serviceDescriptionPath)
      val buildFilePath = serviceDirectoryPath.resolve(service.build.file)

      val imageName = "${workspace.defaultImagePrefix}/${service.build.image}"

      val job = BuildEngineJob(
        serviceDirectoryPath,
        buildFilePath,
        imageName
      )

      buildEngine.engine.build(job)
    }
  }
}
