plugins {
  kotlin("jvm") version "1.5.21"
  kotlin("plugin.serialization") version "1.5.21"

  application
}

repositories {
  mavenCentral()
}

fun kotlinx(name: String, version: String): String = "org.jetbrains.kotlinx:kotlinx-$name:$version"

dependencies {
  api(kotlinx("serialization-json", "1.2.2"))
  api(kotlinx("coroutines-core", "1.5.1"))
  api("io.fabric8:kubernetes-client:5.7.0")

  implementation("com.github.ajalt.clikt:clikt:3.2.0")

  implementation("org.slf4j:slf4j-api:1.7.32")
  implementation("org.slf4j:slf4j-simple:1.7.32")
}

application {
  mainClass.set("io.kosl.Main")
}

distributions.main.configure {
  contents {
    from("examples") {
      into("examples")
    }
  }
}

tasks.withType<Wrapper> {
  gradleVersion = "7.2"
  distributionType = Wrapper.DistributionType.ALL
}
