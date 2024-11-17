import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	java
	id("maven-publish")
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "fr.lorentz.rpi"
version = "0.0.2-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

tasks.getByName<BootJar>("bootJar") {
	archiveFileName.set("home-dashboard-${project.version}.jar") // Nom du fichier JAR exécutable
}

tasks.withType<Sign>().configureEach {
	isEnabled = false
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.thymeleaf.extras:thymeleaf-extras-java8time:3.0.4.RELEASE")
	implementation("org.springframework.boot:spring-boot-starter-web")
	compileOnly("org.projectlombok:lombok:1.18.36")
	annotationProcessor("org.projectlombok:lombok:1.18.36")
	implementation("org.apache.commons:commons-lang3:3.17.0")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testCompileOnly("org.projectlombok:lombok:1.18.36")
	testAnnotationProcessor("org.projectlombok:lombok:1.18.36")

	implementation("org.seleniumhq.selenium:selenium-java:4.25.0")

	developmentOnly("org.springframework.boot:spring-boot-devtools")

}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/bjlorentz/home-dashboard")
	    credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
	publications {
        register<MavenPublication>("gpr") {
			artifact(tasks["bootJar"])
			// Empêche Gradle de générer des fichiers de hachage/signature
			pom.withXml {
				asNode().appendNode("packaging", "jar")
			}
        }
    }
}

tasks.withType<Test> {
	useJUnitPlatform()
}
