package io.kosl.tool

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.requireObject

class BuildCommand: CliktCommand(name = "build", help = "Build Container Images") {
  val context by requireObject<KoslContext>()

  override fun run() {
    for (service in context.workspace.services) {
      context.buildEngine.build(service.createBuildJob())
    }
  }
}
