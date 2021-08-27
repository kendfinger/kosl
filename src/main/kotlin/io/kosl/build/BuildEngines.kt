@file:Suppress("unused")
package io.kosl.build

enum class BuildEngines(val id: String, val engine: BuildEngine) {
  DockerBuild("docker-build", DockerBuildEngine()),
  DockerBuildx("docker-buildx", DockerBuildxEngine()),
  BuildKit("buildkit", BuildKitEngine()),
  Buildah("buildah", BuildahEngine())
}
