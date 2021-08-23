package io.kosl.state

import io.fabric8.kubernetes.api.model.apps.Deployment
import io.fabric8.kubernetes.api.model.apps.DeploymentBuilder
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

  fun createBuildJob(push: Boolean = false): BuildEngineJob = BuildEngineJob(
    serviceDirectoryPath,
    buildFilePath,
    imageName,
    push
  )

  fun renderKubernetesResource(): Deployment {
    return DeploymentBuilder()
      .withNewMetadata()
        .withName(spec.name)
      .endMetadata()
      .withNewSpec()
        .withReplicas(spec.deployment.replicas)
        .withNewTemplate()
          .withNewSpec()
            .addNewContainer()
              .withName("service")
              .withImage(imageName)
              .withImagePullPolicy("Always")
            .endContainer()
          .endSpec()
        .endTemplate()
      .endSpec()
      .build()
  }
}
