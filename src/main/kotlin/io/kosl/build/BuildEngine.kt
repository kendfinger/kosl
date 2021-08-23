package io.kosl.build

interface BuildEngine {
  fun process(job: BuildEngineJob)
}
