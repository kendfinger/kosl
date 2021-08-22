package io.kosl.tool

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.requireObject
import io.fabric8.kubernetes.client.utils.Serialization

class GenerateKubernetesCommand: CliktCommand(name = "generate-kubernetes", help = "Generate Kubernetes Resources") {
  val context by requireObject<KoslContext>()

  override fun run() {
    for (service in context.workspace.services) {
      val deployment = service.renderKubernetesYaml()
      println(Serialization.asYaml(deployment).trim())
    }
  }
}
