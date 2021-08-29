@file:Suppress("unused")
package io.kosl.build

import io.kosl.build.engines.*

enum class BuildEngines(val id: String, val engine: BuildEngine) {
  DockerBuild("docker-build", DockerBuildEngine()),
  DockerBuildx("docker-buildx", DockerBuildxEngine()),
  BuildKit("buildkit", BuildKitEngine()),
  Buildah("buildah", BuildahEngine()),
  Podman("podman", PodmanEngine())
}
