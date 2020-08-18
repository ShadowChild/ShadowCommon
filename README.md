# Cybernize [![Build Status](https://travis-ci.org/ShadowChild/Cybernize2.svg?branch=master)](https://travis-ci.org/ShadowChild/Cybernize2)
A common library i'll be using for my projects

To implement this into your project, you have two choices, either though adding a jar as a dependancy, or by using a dependency management system. It is recommended that you use a dependency management system such as Maven, or Gradle as this can greatly help with the deployment of your programs, and/or when others try to contribute to your project.

Below is how you would implement this project into either maven or gradle

### Maven
```xml
<repositories>
        <repository>
                <name>Cybernize repo</name>
                <url>https://dl.bintray.com/candor/cybernize</url>
        </repository>
</repositories>

<properties>
        <cynbernize.version>1.0</cynbernize.version>
</properties>

<dependencies>
  <dependency>
    <groupId>me.github.shadowchild</groupId>
    <artifactId>cybernize2</artifactId>
    <version>${cybernize.version}</version>
  </dependency>
</dependencies>
```

## Gradle
```groovy
repositories {

    maven { url = "https://dl.bintray.com/candor/cybernize" }
}

project.ext.cybernizeVersion = "1.0"

dependencies {

    implmentation "me.github.shadowchild:cybernize2:${cybernizeVersion}"
}
```
