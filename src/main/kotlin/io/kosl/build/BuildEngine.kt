package io.kosl.build

interface BuildEngine {
  fun build(job: BuildEngineJob)
}
