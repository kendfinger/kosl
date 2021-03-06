package io.kosl.spec

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import io.kosl.io.Path

open class KoslSpec<T>(private val serializer: KSerializer<T>) {
  fun loadFromPath(path: Path): T {
    return loadFromString(Files.readString(path, StandardCharsets.UTF_8))
  }

  fun loadFromString(content: String): T {
    return Json.decodeFromString(serializer, content)
  }
}
