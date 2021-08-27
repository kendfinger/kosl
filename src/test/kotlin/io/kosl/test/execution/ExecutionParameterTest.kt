package io.kosl.test.execution

import io.kosl.execution.*
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

    val expanded = parameters.expand()
    assertEquals(parameters[0], expanded[0])
    assertEquals(parameters[1], expanded[1])
    assertEquals((parameters[1] as JoinedParameter).left, expanded[2])
    assertEquals((parameters[1] as JoinedParameter).right, expanded[3])
    assertEquals(parameters[2], expanded[4])
  }
}
