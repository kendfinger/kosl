package io.kosl.tool

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.findOrSetObject
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.enum
import com.github.ajalt.clikt.parameters.types.path
import io.kosl.context.KoslContext
import io.kosl.build.BuildEngines
import io.kosl.render.WorkspaceRenderer
import io.kosl.spec.WorkspaceSpec
import java.nio.file.Paths

class KoslCommand: CliktCommand() {
  init {
    subcommands(
      BuildCommand(),
      GenerateKubernetesCommand()
    )
  }

  val buildEngine by option("-b", "--build-engine", help = "Container Build Engine")
    .enum<BuildEngines> { it.id }
    .default(BuildEngines.DockerBuild)

  val workspaceDirectoryPath by option("-w", "--workspace", help = "Path to Workspace")
    .path(mustExist = true, canBeFile = false)
    .default(Paths.get(""))

  val tag by option("-t", "--tag", help = "Image Tag")
    .default("latest")

  val overrideImagePrefix by option("-P", "--image-prefix", help = "Override Image Prefix")

  val isDryRun by option("--dry-run", help = "Perform a Dry Run")
    .flag()

  val context by findOrSetObject { KoslContext() }

  override fun run() {
    context.workspaceDirectoryPath = workspaceDirectoryPath
    context.isDryRun = isDryRun
    context.overrideImagePrefix = overrideImagePrefix
    context.tag = tag

    val workspaceSpecPath = workspaceDirectoryPath.resolve("kosl-workspace.json")
    val workspaceSpec = WorkspaceSpec.loadFromPath(workspaceSpecPath)
    val workspaceRenderer = WorkspaceRenderer(context)
    context.workspaceState = workspaceRenderer.render(workspaceSpec)
    context.buildEngine = buildEngine.engine
  }
}
