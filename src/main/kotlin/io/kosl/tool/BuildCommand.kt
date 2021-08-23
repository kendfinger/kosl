package io.kosl.tool

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.requireObject
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import io.kosl.context.KoslContext

class BuildCommand: CliktCommand(name = "build", help = "Build Container Images") {
  val context by requireObject<KoslContext>()

  val push by option("--push", help = "Push Built Images").flag()

  override fun run() {
    for (service in context.workspaceState.services) {
      context.buildEngine.process(context, service.createBuildJob(push = push))
    }
  }
}
