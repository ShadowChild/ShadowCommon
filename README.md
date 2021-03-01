# Cybernize [![Build Status](https://travis-ci.org/ShadowChild/Cybernize2.svg?branch=master)](https://travis-ci.org/ShadowChild/Cybernize2)[ ![Download](https://api.bintray.com/packages/candor/cybernize/cybernize/images/download.svg) ](https://bintray.com/candor/cybernize/cybernize/_latestVersion)
A common library i'll be using for my projects

To implement this into your project, you have two choices, either though adding a jar as a dependancy, or by using a dependency management system. It is recommended that you use a dependency management system such as Maven, or Gradle as this can greatly help with the deployment of your programs, and/or when others try to contribute to your project.

Below is how you would implement this project into either maven or gradle

### Maven
```xml
<repositories>
        <repository>
                <id>innoxium</id>
                <name>Innoxium repo</name>
                <url>https://repo.repsy.io/mvn/innoxium/innoxium</url>
        </repository>
</repositories>

<properties>
        <cynbernize.version>1.1.5</cynbernize.version>
</properties>

<dependencies>
  <dependency>
    <groupId>uk.co.innoxium.cybernize</groupId>
    <artifactId>cybernize</artifactId>
    <version>${cybernize.version}</version>
  </dependency>
</dependencies>
```

## Gradle
```groovy
repositories {

    maven { url = "https://repo.repsy.io/mvn/innoxium/innoxium" }
}

project.ext.cybernizeVersion = "1.1.5"

dependencies {

    implmentation "uk.co.innoxium.cybernize:cybernize:${cybernizeVersion}"
}
```
