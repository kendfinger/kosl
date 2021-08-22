package io.kosl.tool

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.findOrSetObject
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.enum
import com.github.ajalt.clikt.parameters.types.path
import io.kosl.build.BuildEngines
import io.kosl.spec.WorkspaceSpec
import io.kosl.spec.render
import java.nio.file.Paths

class KoslCommand: CliktCommand() {
  init {
    subcommands(
      BuildCommand(),
      GenerateKubernetesCommand()
    )
  }

  val buildEngine by option("-b", "--build-engine")
    .enum<BuildEngines> { it.id }
    .default(BuildEngines.DockerBuild)

  val workspaceDirectoryPath by option("-w", "--workspace")
    .path(mustExist = true, canBeFile = false)
    .default(Paths.get(""))

  val context by findOrSetObject { KoslContext() }

  override fun run() {
    val workspaceSpecPath = workspaceDirectoryPath.resolve("kosl-workspace.json")
    val workspaceSpec = WorkspaceSpec.loadFromPath(workspaceSpecPath)
    context.workspace = workspaceSpec.render(workspaceDirectoryPath)
    context.buildEngine = buildEngine.engine
  }
}
