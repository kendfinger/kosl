package io.kosl.test

import io.kosl.spec.ServiceSpec
import io.kosl.spec.WorkspaceSpec
import kotlinx.serialization.json.Json
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.absolutePathString

class KoslTestEnvironment {
  val workspaceDirectoryPath: Path = Files.createTempDirectory("kosl-test")

  fun populate(block: KoslTestPopulate.() -> Unit) {
    val test = KoslTestPopulate(this)
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
    fun populated(block: KoslTestPopulate.() -> Unit): KoslTestEnvironment {
      val environment = KoslTestEnvironment()
      environment.populate(block)
      return environment
    }
  }
}

class KoslTestPopulate(val environment: KoslTestEnvironment) {
  var spec: WorkspaceSpec? = null

  fun service(name: String, block: KoslTestService.() -> Unit) {
    val test = KoslTestService(environment, name)
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

class KoslTestService(val environment: KoslTestEnvironment, val name: String) {
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
