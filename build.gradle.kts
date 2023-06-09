plugins {
    kotlin("jvm") version "1.9.0"
    application
    kotlin("plugin.serialization") version "1.9.0"
    id("net.mamoe.mirai-console") version "2.14.0"
}

group = "net.cutereimu.hkbot"
version = "1.0.1"

repositories {
    maven("https://maven.aliyun.com/repository/public")
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
}

mirai {
    jvmTarget = JavaVersion.VERSION_17
}
