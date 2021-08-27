package io.kosl.test

import io.kosl.spec.ServiceSpec
import io.kosl.spec.WorkspaceSpec
import kotlinx.serialization.json.Json
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.absolutePathString

class KoslTestWorkspace {
  val workspaceDirectoryPath: Path = Files.createTempDirectory("kosl-test")

  fun populate(block: KoslTestWorkspaceDsl.() -> Unit) {
    val test = KoslTestWorkspaceDsl(this)
    block(test)
    test.apply()
  }

  fun destroy() {
    if (!File(workspaceDirectoryPath.absolutePathString()).deleteRecursively()) {
      throw Exception(
        "Failed to cleanup test environment path: " +
        workspaceDirectoryPath.absolutePathString())
    }
  }

  fun write(path: String, content: String) {
    val actualFilePath = workspaceDirectoryPath.resolve(path)
    Files.createDirectories(actualFilePath.parent)
    Files.writeString(actualFilePath, content)
  }

  companion object {
    fun populated(block: KoslTestWorkspaceDsl.() -> Unit): KoslTestWorkspace {
      val environment = KoslTestWorkspace()
      environment.populate(block)
      return environment
    }
  }

  class KoslTestWorkspaceDsl(val environment: KoslTestWorkspace) {
    var spec: WorkspaceSpec? = null

    fun service(name: String, block: KoslTestServiceDsl.() -> Unit) {
      val test = KoslTestServiceDsl(environment, name)
      block(test)
      test.apply()
    }

    fun apply() {
      environment.write(
        "kosl-workspace.json",
        Json.encodeToString(WorkspaceSpec.serializer(), spec!!)
      )
    }
  }

  class KoslTestServiceDsl(val environment: KoslTestWorkspace, val name: String) {
    var spec: ServiceSpec? = null

    fun file(path: String, content: String) {
      environment.write("${name}/${path}", content)
    }

    fun apply() {
      environment.write(
        "${name}/kosl-service.json",
        Json.encodeToString(ServiceSpec.serializer(), spec!!)
      )
    }
  }
}
