plugins {
    application
    scala
    jacoco
    id("org.danilopianini.git-sensitive-semantic-versioning")
    id("cz.alenkacz.gradle.scalafmt") version "1.5.1"
}

group = "io.github.enrignagna"



repositories {
    jcenter()
    mavenLocal()
    mavenCentral()
}

dependencies {
    val scalaVersion = "2.12"

    //TODO: Remove imperative part of scala version
    //implementation("ch.epfl.scala:scalafix-core_2.12:_")
    implementation("org.mongodb.scala:mongo-scala-driver_2.12:_")
    implementation("io.spray:spray-json_2.12:_")
    implementation("com.lightbend.akka:akka-stream-alpakka-mongodb_2.12:_")
    implementation("com.typesafe.akka:akka-stream_2.12:_")
    implementation("com.typesafe.akka:akka-slf4j_2.12:_")
    implementation("org.scala-lang:scala-library:2.12")

    testImplementation("junit:junit:4.13")
    testImplementation("org.scalatest:scalatest_2.12:_")
    testImplementation("org.scalatestplus:junit-4-12_2.12:_")

    testRuntimeOnly("org.scala-lang.modules:scala-xml_2.12:_")
}


tasks.jacocoTestReport {
    reports {
        xml.isEnabled = true
        xml.destination = file("${buildDir}/reports/jacoco/report.xml")
        html.isEnabled = true
        html.destination = file("${buildDir}/reports/jacoco/jacocoHtml")
    }
}

scalafmt {
    // .scalafmt.conf in the project root is default value, provide only if other location is needed
    // config file has to be relative path from current project or root project in case of multimodule projects
    // example usage:
    // configFilePath = ".scalafmt.conf"
}

gitSemVer {
    version = computeGitSemVer()
}

