plugins {
    kotlin("jvm") version "1.9.23"
    application
    kotlin("plugin.serialization") version "1.9.23"
    id("net.mamoe.mirai-console") version "2.15.0"
}

group = "net.cutereimu.hkbot"
version = "1.0.1"

repositories {
    maven("https://maven.aliyun.com/repository/public")
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
}

mirai {
    jvmTarget = JavaVersion.VERSION_17
}
