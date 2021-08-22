package io.kosl.state

import io.kosl.build.BuildEngineJob
import io.kosl.spec.ServiceSpec
import io.kosl.spec.WorkspaceSpec
import java.nio.file.Path

class ServiceState(
  val workspaceSpec: WorkspaceSpec,
  val spec: ServiceSpec,
  val serviceDirectoryPath: Path,
) {
  val buildFilePath: Path = serviceDirectoryPath.resolve(spec.build.file)
  val imageName: String = "${workspaceSpec.defaultImagePrefix}/${spec.build.image}"

  fun createBuildJob(): BuildEngineJob = BuildEngineJob(
    serviceDirectoryPath,
    buildFilePath,
    imageName
  )
}
