dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    // json parsing
    implementation("com.googlecode.json-simple:json-simple:1.1.1")
    // xml
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.18.3")
    // cache
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("com.github.ben-manes.caffeine:caffeine:3.1.8")
    // jwt
    implementation("io.jsonwebtoken:jjwt:0.12.5")

    testRuntimeOnly("com.h2database:h2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
}
