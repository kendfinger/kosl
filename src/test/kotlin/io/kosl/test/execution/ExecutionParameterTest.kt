package io.kosl.test.execution

import io.kosl.execution.*
import io.kosl.execution.parameter.CommandName
import io.kosl.execution.parameter.JoinedParameter
import io.kosl.execution.parameter.RawArgument
import io.kosl.execution.parameter.RelativePath
import java.io.File
import java.nio.file.Paths
import kotlin.test.Test
import kotlin.test.assertEquals

class ExecutionParameterTest {
  @Test
  fun `complex parameter sets are expanded properly`() {
    val parameters = listOf(
      CommandName("git"),
      JoinedParameter(
        RawArgument("-C"),
        RelativePath(Paths.get("hello-world.git"))
      ),
      RawArgument("push")
    )

    val job = ExecutionJob(parameters, File("").absoluteFile.toPath())
    val expanded = job.expandSubParameters()

    assertEquals(parameters[0], expanded[0])
    assertEquals(parameters[1], expanded[1])
    assertEquals((parameters[1] as JoinedParameter).left, expanded[2])
    assertEquals((parameters[1] as JoinedParameter).right, expanded[3])
    assertEquals(parameters[2], expanded[4])
  }
}
