plugins {
    kotlin("jvm") version "1.5.10"
}

group = "ru.donz.wf"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val versionsScalaBinary: String = "2.13"

dependencies {
    implementation(platform("com.typesafe.akka:akka-bom_${versionsScalaBinary}:2.6.15"))

    implementation("ch.qos.logback:logback-classic:1.2.4")
    implementation("org.slf4j:slf4j-api:1.7.31")
    implementation("com.typesafe.akka:akka-actor-typed_${versionsScalaBinary}")
    implementation("com.typesafe.akka:akka-cluster-typed_${versionsScalaBinary}")
    implementation("com.typesafe.akka:akka-cluster-sharding-typed_${versionsScalaBinary}")
    implementation("com.typesafe.akka:akka-persistence-typed_${versionsScalaBinary}")

    testImplementation("com.typesafe.akka:akka-actor-testkit-typed_${versionsScalaBinary}")
    testImplementation("com.typesafe.akka:akka-persistence-testkit_${versionsScalaBinary}")

    compileOnly("org.projectlombok:lombok:1.18.20")
    annotationProcessor("org.projectlombok:lombok:1.18.20")

    testCompileOnly("org.projectlombok:lombok:1.18.20")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.20")
}
dependencies {
    implementation(kotlin("stdlib"))
}