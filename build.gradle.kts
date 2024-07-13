plugins {
    kotlin("jvm") version "2.0.0"
}

group = "com.github.phenixfine"
version = "1.0-SNAPSHOT"

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.0.0")
    implementation("commons-io:commons-io:2.6")
    implementation("com.github.ajalt:clikt:1.3.0")

    //TODO 1.only jdk 1.8 !! 2. https://blog.csdn.net/weixin_44147584/article/details/115599699
    implementation(files("C:\\Progra~1\\Android\\Android Studio\\jre\\jre\\lib\\rt.jar"))
    testImplementation(kotlin("test"))
    testImplementation("junit:junit:4.+")
    testImplementation("org.jetbrains.kotlin:kotlin-test:2.0.0")
}