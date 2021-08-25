package io.kosl.test

import io.kosl.spec.BuildSpec
import io.kosl.spec.DeploymentSpec
import io.kosl.spec.ServiceSpec
import io.kosl.spec.WorkspaceSpec

fun createHelloWorldEnvironment() = KoslTestEnvironment.populated {
  spec = WorkspaceSpec("ghcr.io/kendfinger/kosl/test", listOf(
    "hello-world"
  ))

  service("hello-world") {
    spec = ServiceSpec(
      name = "hello-world",
      build = BuildSpec(
        image = "hello-world"
      ),
      deployment = DeploymentSpec(
        replicas = 3
      )
    )

    file("Dockerfile", """
      FROM docker.io/library/busybox:latest
      CMD ["/bin/echo", "Hello World"]
    """.trimIndent())
  }
}
