package io.kosl.tool

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.requireObject
import io.fabric8.kubernetes.client.utils.Serialization
import io.kosl.context.KoslContext

class GenerateKubernetesCommand: CliktCommand(name = "generate-kubernetes", help = "Generate Kubernetes Resources") {
  val context by requireObject<KoslContext>()

  override fun run() {
    for (service in context.workspaceState.services) {
      val deployment = service.renderKubernetesResource()
      println(Serialization.asYaml(deployment).trim())
    }
  }
}
