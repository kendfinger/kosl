package io.kosl.build

import io.kosl.context.KoslContext

interface BuildEngine {
  fun process(context: KoslContext, job: BuildEngineJob)
}
