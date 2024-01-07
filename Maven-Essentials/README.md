# What is Maven?

- Maven is a build automation tool used primarily for Java projects. Maven can also be used to build and manage projects
  written in C#, Ruby, Scala, and other languages. The Maven project is hosted by the Apache Software Foundation, where
  it
  was formerly part of the Jakarta Project.

- Maven addresses two aspects of building software:
    1. first, it describes how software is built,
    2. and second, it describes its dependencies.

- Contrary to preceding tools like Apache Ant, it uses conventions for the build procedure, and only
  exceptions need to be written down. An XML file describes the software project being built, its dependencies on other
  external modules and components, the build order, directories, and required plug-ins. It comes with pre-defined
  targets
  for performing certain well-defined tasks such as compilation of code and its packaging.

- Maven dynamically downloads Java libraries and Maven plug-ins from one or more repositories such as the Maven 2
  Central
  Repository, and stores them in a local cache. Maven projects are configured using a Project Object Model, which is
  stored in a [pom.xml](./calculator-app/pom.xml) file. The POM contains the project's configuration and dependencies which are used to
  build the project. It contains default values for most projects.

- Examples for this are the build directory, which is
  target; the source directory, which is `src/main/java`; the test source directory, which is `src/test/java`; and so
  on.
  When executing a task or goal, Maven looks for the POM in the current directory. It reads the POM, gets the needed
  configuration information, then executes the goal. Plugins can extend Maven to utilize a number of other
  development tools for reporting or the build process.

---

## What is Maven used for?

- Maven is a powerful project management tool based on POM (project object model). It is used for projects build,
  dependency and documentation. It simplifies the build process like ANT. But it is too much advanced than ANT.
- It is open source and developed by Apache Software Foundation.
- It is written in java language, hence it is a cross-platform tool. It builds software projects written in C#, Ruby, Scala,
  and other languages.

### Characteristics of Maven

- **Convention over configuration**: Maven provides a set of conventions. For example, if we put the source code in
  `src/main/java`, then we don't need to configure for building the project because Maven knows how to build the project.

- **Consistency**: Maven provides a uniform build system. If we build 10 projects with Maven, then we will get the same
  output every time.

- **Configuration**: Maven uses a `pom.xml` file to configure the project. It provides a Project Object Model (POM) file
  to manage the project's build, dependencies, reporting, and documentation.

- **IDE agnostic**: Maven is IDE agnostic, which means we can use any IDE to build our project. It is not necessary to
  use the IDE's build system.

- **IDE integration**: Maven provides plugins for various IDEs like Eclipse, Netbeans, IntelliJ, etc. to integrate with
  Maven.

- **Declarative Dependency Management**: Maven provides a high-level declarative model for dependency management. We
  need to define all the dependencies in a `pom.xml` file and Maven manages the rest.

- **Plugin Based Architcture**: Maven provides a plugin-based architecture to help us to write any task. We can write
  our own plugin or we can use already existing plugins.

---

## The Project structure of Java

This is a basic structure, and real-world projects might include additional directories and files based on their requirements. The actual structure can also be influenced by the choice of build tools (like Maven or Gradle) or frameworks (like Spring Boot).

    MyJavaProject
    ├── src
    │   ├── main
    │   │   ├── java
    │   │   │   └── com
    │   │   │       └── mycompany
    │   │   │           └── myjavaproject
    │   │   │               ├── controllers        // Java classes handling web requests
    │   │   │               ├── models             // POJOs (Plain Old Java Objects) representing data entities
    │   │   │               ├── repositories       // Data access layer (e.g., using Spring Data)
    │   │   │               ├── services           // Business logic layer
    │   │   │               └── Application.java   // Main class with the main method to run the application
    │   │   ├── resources
    │   │   │   ├── application.properties        // Configuration properties (database, server settings, etc.)
    │   │   │   ├── static                        // Static resources (CSS, JS, images)
    │   │   │   └── templates                     // HTML templates (if using a template engine)
    │   ├── test
    │   │   └── java
    │   │       └── com
    │   │           └── mycompany
    │   │               └── myjavaproject
    │   │                   ├── controllers        // Test classes for controllers
    │   │                   ├── models             // Test classes for models
    │   │                   ├── repositories       // Test classes for repositories
    │   │                   └── services           // Test classes for services
    ├── target                                   // Compiled bytecode and packaged JARs (generated by the build tool)
    ├── pom.xml                                  // Project Object Model file for Maven (dependency and build configuration)
    ├── .gitignore                               // Specifies intentionally untracked files to be ignored by Git
    └── README.md                                // Project documentation

---

## Generating a Maven project

- To generate a Maven project, we can use the `mvn archetype:generate` command and select the `maven-archetype-quickstart` archetype from the list of available archetypes.

- The `maven-archetype-quickstart` archetype is a simple Maven project with a predefined directory structure and a `pom.xml` file with some default configuration.

- The `mvn archetype:generate` command will prompt us to enter the `groupId`, `artifactId`, and `version` of the project. These values will be used to generate the `pom.xml` file.

- The `groupId` is a unique identifier for the project's group. It is usually based on the organization's domain name in reverse order. For example, if the organization's domain name is `mycompany.com`, then the `groupId` can be `com.mycompany`.

- The `artifactId` is a unique identifier for the project. It is usually based on the project's name. For example, if the project's name is `myjavaproject`, then the `artifactId` can be `myjavaproject`.

- The `version` is the version of the project. It is usually in the format `major.minor.patch`. For example, if the project's version is `1.0.0`, then the `version` can be `1.0.0`.

- The `mvn archetype:generate` command will also prompt us to enter the `package` name. The `package` name is the fully qualified name of the project's main class. For example, if the `groupId` is `com.mycompany` and the `artifactId` is `myjavaproject`, then the `package` name can be `com.mycompany.myjavaproject`.

- The `mvn archetype:generate` command will generate a project with the following directory structure:

        myjavaproject
        ├── src
        │   ├── main
        │   │   └── java
        │   │       └── com
        │   │           └── mycompany
        │   │               └── myjavaproject
        │   │                   └── App.java
        │   └── test
        │       └── java
        │           └── com
        │               └── mycompany
        │                   └── myjavaproject
        │                       └── AppTest.java
        ├── pom.xml
        └── README.md

```shell
# Generate a Maven project
mvn archetype:generate

# Choose archetype:
# 1: remote -> org.apache.maven.archetypes:maven-archetype-archetype (An archetype which contains a sample archetype.)
# 2: remote -> org.apache.maven.archetypes:maven-archetype-j2ee-simple (An archetype which contains a simplifed sample J2EE application.)
# 3: remote -> org.apache.maven.archetypes:maven-archetype-plugin (An archetype which contains a sample Maven plugin.)
# 4: remote -> org.apache.maven.archetypes:maven-archetype-plugin-site (An archetype which contains a sample Maven plugin site.
#       This archetype can be layered upon an existing Maven plugin project.)
# 5: remote -> org.apache.maven.archetypes:maven-archetype-portlet (An archetype which contains a sample JSR-268 Portlet.)
# 6: remote -> org.apache.maven.archetypes:maven-archetype-profiles ()
# 7: remote -> org.apache.maven.archetypes:maven-archetype-quickstart (An archetype which contains a sample Maven project.)

Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains): 7: 7

Choose org.apache.maven.archetypes:maven-archetype-quickstart version: 1: 1

Define value for property 'groupId': com.mycompany
Define value for property 'artifactId': myjavaproject
Define value for property 'version' 1.0-SNAPSHOT: :
Define value for property 'package' com.mycompany: :
```

---

## The Lifecycle of Maven Project

- Maven is based on the concept of a project object model (POM). A POM file is an XML representation of a Maven project held in a file named `pom.xml`.

- The POM contains references to all of the project's dependencies (external JAR files) and lists the project's goals and plugins.

- The POM also contains the project's configuration, such as the project's build directory, source directory, test source directory, and so on. The POM is the core of a Maven project, and it is used by Maven to manage the project's build, dependencies, reporting, and documentation.

- The POM is located in the project's base directory. The `mvn` command must be executed in the project's base directory, where the `pom.xml` file is located.

- The `mvn` command can be used to execute a goal or a phase. A goal is a task that can be executed. A phase is a step in the build lifecycle.

- The `mvn` command can also be used to execute a plugin goal. A plugin is a collection of goals. For example, the `maven-compiler-plugin` is a plugin that contains the `compile` goal. The `mvn` command can also be used to execute a profile. A profile is a set of configuration values that can be used to customize the build for different environments (development, testing, production, etc.).

### Compiling a Maven Project

- The `mvn compile` command will compile the project's source code and generate the compiled bytecode in the `target/classes` directory.

- The `mvn compile` command will also compile the project's test source code and generate the compiled bytecode in the `target/test-classes` directory.

```shell
# Compile the project's source code
mvn compile

# Test the project's source code
mvn test
```

### Packaging a Maven Project (Generating Artifact)

- The `mvn package` command will compile the project's source code and test source code, run the project's tests, and package the compiled bytecode into a JAR file.

- The `mvn package` command will generate the JAR file in the `target` directory.

```shell
# Package the project's source code and test source code
mvn package
```

---

## Dependency Management

- Maven provides a high-level declarative model for dependency management. We need to define all the dependencies in a `pom.xml` file and Maven manages the rest.

- Maven uses the `groupId`, `artifactId`, and `version` to identify a dependency. The `groupId` is the group identifier of the dependency. The `artifactId` is the artifact identifier of the dependency. The `version` is the version of the dependency and to download the dependency from a repository. A repository is a collection of dependencies. The Maven Central Repository is a public repository that contains a large number of dependencies.

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-core</artifactId>
    <version>5.3.9</version>
</dependency>
```

- The `mvn dependency:tree` command will display the dependency tree of the project.

```shell
# Display the dependency tree of the project
mvn dependency:tree
```

### Transitive Dependencies

- Maven can automatically download the dependencies of a dependency. For example, if the project depends on `spring-webmvc`, then Maven will automatically download the `spring-core` dependency because `spring-webmvc` depends on `spring-core`.

### Dependency Meditation

- Maven can automatically resolve the version conflicts of dependencies. For example, if the project depends on `spring-core:5.3.9` and `spring-webmvc:5.3.9`, then Maven will automatically resolve the version conflict and use `spring-core:5.3.9` for both dependencies.

- Maven uses the **nearest definition strategy** to resolve the version conflicts of dependencies. For example, if the project depends on `spring-core:5.3.9` and `spring-webmvc:5.3.8`, then Maven will use `spring-webmvc:5.3.8` because it is the nearest definition.

- Maven can also be configured to use the **first declaration strategy** to resolve the version conflicts of dependencies. For example, if the project depends on `spring-core:5.3.9` and `spring-webmvc:5.3.8`, then Maven will use `spring-core:5.3.9` because it is the first declaration.

- What if we need to override the version of a dependency? For example, if the project depends on `spring-core:5.3.9` and `spring-webmvc:5.3.8`, but we want to use `spring-core:5.3.8` for both dependencies. We can use the `<dependencyManagement>` element to override the version of a dependency.

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>5.3.8</version>
        </dependency>
    </dependencies>
</dependencyManagement>
```

- Maven can also be configured to exclude a dependency. For example, if the project depends on `spring-core:5.3.9` and `spring-webmvc:5.3.8`, but we don't want to use `spring-core` for `spring-webmvc`. We can use the `<exclusions>` element to exclude a dependency.

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.3.8</version>
        <exclusions>
            <exclusion>
                <groupId>org.springframework</groupId>
                <artifactId>spring-core</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
</dependencies>
```

---

## Dependency Scope

- Maven provides a set of dependency scopes to control the visibility of a dependency. The dependency scopes are `compile`, `provided`, `runtime`, `test`, `system`, and `import`.

- The `compile` scope is the default scope. It is used to compile the project's source code and test source code. It is also used to package the compiled bytecode into a JAR file.

- The `provided` scope is used to compile the project's source code and test source code. It is also used to package the compiled bytecode into a JAR file. However, it is not included in the project's classpath at runtime. It is used to indicate that the dependency will be provided by the JDK or the container at runtime.

- The `runtime` scope is used to compile the project's source code and test source code. It is also used to package the compiled bytecode into a JAR file. However, it is not included in the project's classpath at compile time. It is used to indicate that the dependency will be available at runtime.

- The `test` scope is used to compile the project's test source code. It is also used to package the compiled bytecode into a JAR file. However, it is not included in the project's classpath at compile time. It is used to indicate that the dependency will be available for testing.

- The `system` scope is used to compile the project's source code and test source code. It is also used to package the compiled bytecode into a JAR file. However, it is not included in the project's classpath at compile time. It is used to indicate that the dependency will be available at runtime. It is similar to the `provided` scope, but it is used to indicate that the dependency will not be provided by the JDK or the container at runtime.

- The `import` scope is used to import dependencies from other POM files. It is used to indicate that the dependency will be imported from another POM file.

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-core</artifactId>
        <version>5.3.9</version>
        <scope>compile</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.3.9</version>
        <scope>provided</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-jdbc</artifactId>
        <version>5.3.9</version>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-test</artifactId>
        <version>5.3.9</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>5.3.9</version>
        <scope>system</scope>
        <systemPath>${project.basedir}/lib/spring-context-5.3.9.jar</systemPath>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>5.3.9</version>
        <scope>import</scope>
    </dependency>
</dependencies>
```

---

## Declaring and Using Properties

- Maven provides a set of properties to configure the project. The properties can be used to configure the project's build, dependencies, reporting, and documentation.

- The properties can be declared in the `<properties>` element. The properties can be used in the `pom.xml` file using the `${property}` syntax.

```xml
<properties>
    <java.version>11</java.version>
    <spring.version>5.3.9</spring.version>
</properties>

<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-core</artifactId>
        <version>${spring.version}</version>
        <scope>compile</scope>
    </dependency>
</dependencies>
```

---

## Maven Local Repository

- Maven uses a local repository to store the dependencies of a project. The local repository is located in the `~/.m2/repository` directory.

- The local repository contains the dependencies of all the projects that we have built using Maven. The local repository is also used to cache the dependencies of all the projects that we have downloaded using Maven.

- The local repository can be configured in the `~/.m2/settings.xml` file. The `~/.m2/settings.xml` file is located in the `~/.m2` directory.

```xml
<settings>
    <localRepository>/path/to/local/repository</localRepository>
</settings>
```

---

## Enterprise Maven Repository Architecture

    +-----------------------------------------------------------+
    |                    Enterprise                             |
    |                    Maven Repository                       |
    |                                                           |
    |     +---------------------------------------------+       |
    |     |                Nexus/Artifactory            |       |
    |     |         (Or any other Repository Manager)   |       |
    |     +---------------------------------------------+       |
    |                  ^                       ^                |
    |                  |                       |                |
    |            +-----|-----------------------|-----+          |
    |            |                                   |          |
    |     +------v--------+                  +-------v-------+  |
    |     |  Custom       |                  |  Custom       |  |
    |     |  Artifacts    |                  |  Artifacts    |  |
    |     +---------------+                  +---------------+  |
    |                                                           |
    +-----------------------------------------------------------+
    |                                                           |
    |                Enterprise Developers                      |
    |                                                           |
    |  +-----------------------------------------------+        |
    |  |               Developer's Projects            |        |
    |  |                                               |        |
    |  |     +-------------------------------+         |        |
    |  |     |       Maven Project           |         |        |
    |  |     |                               |         |        |
    |  |     |  +------------------------+   |         |        |
    |  |     |  |      Dependencies      |   |         |        |
    |  |     |  |                        |   |         |        |
    |  |     |  |  +------------------+  |   |         |        |
    |  |     |  |  | Maven Central    |  |   |         |        |
    |  |     |  |  | Repository       |  |   |         |        |
    |  |     |  |  +------------------+  |   |         |        |
    |  |     |  |                        |   |         |        |
    |  |     |  +------------------------+   |         |        |
    |  |     |                               |         |        |
    |  |     +-------------------------------+         |        |
    |  +-----------------------------------------------+        |
    +-----------------------------------------------------------+

---

## The `mvn install` Command

- The `mvn install` command will compile the project's source code and test source code, run the project's tests, package the compiled bytecode into a JAR file, and install the JAR file into the local repository.

```shell
# Install the project's source code and test source code
mvn install
```

---

## The Maven build lifecycle

### The default Maven build lifecycle

- The Maven build lifecycle consists of three phases: `validate`, `compile`, `test`, `package`, `verify`, `install`, and `deploy`.

- The `validate` phase is used to validate the project's configuration. It is used to validate the project's POM file and the project's dependencies. It is also used to validate the project's source code and test source code.

- The `compile` phase is used to compile the project's source code and test source code. It is also used to package the compiled bytecode into a JAR file.

- The `test` phase is used to run the project's tests.

- The `package` phase is used to package the compiled bytecode into a JAR file.

- The `verify` phase is used to verify the project's package. It is used to verify the project's package against the project's tests.

- The `install` phase is used to install the project's package into the local repository.

- The `deploy` phase is used to deploy the project's package into the remote repository.

### The Clean Maven build lifecycle

- The Maven build lifecycle consists of three phases: `pre-clean`, `clean`, and `post-clean`.

- The `pre-clean` phase is used to clean the project's build directory.

- The `clean` phase is used to clean the project's build directory. Here clean means to delete the project's build directory (e.g., `target` directory).

```shell
# Clean the project's build directory
mvn clean
```

- The `post-clean` phase is used to clean the project's build directory.

### The Site Maven build lifecycle

- The Maven build lifecycle consists of three phases: `pre-site`, `site`, and `post-site`.

- The `pre-site` phase is used to generate the project's site documentation.

- The `site` phase is used to generate the project's site documentation.

```shell
# Generate the project's site documentation
mvn site

# Deploy the project's site documentation
mvn site-deploy
```

- The `post-site` phase is used to generate the project's site documentation.

---

## The Maven plugin

- Maven provides a set of plugins to extend the functionality of Maven. The plugins can be used to perform various tasks such as compiling the project's source code, running the project's tests, packaging the compiled bytecode into a JAR file, and so on.

- The plugins can be configured in the `<plugins>` element. The plugins can be used in the `pom.xml` file using the `<plugin>` element.

```xml
<plugins>
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
    </plugin>
</plugins>
```

- To add a plugin to the project, we need to add the plugin's `<groupId>` and `<artifactId>` to the `<plugins>` element. The `<groupId>` is the group identifier of the plugin. The `<artifactId>` is the artifact identifier of the plugin.

```shell
# Add a plugin to the project
mvn pluginName:phaseName
```

---

## Multi-module Maven Project

- A multi-module Maven project is a Maven project that consists of multiple modules. A module is a Maven project that can be built independently of other modules.

- A multi-module Maven project is useful when we want to build multiple Maven projects together. For example, if we have a Maven project that consists of multiple modules, then we can build all the modules together using the `mvn install` command. The `mvn install` command will compile the project's source code and test source code, run the project's tests, package the compiled bytecode into a JAR file, and install the JAR file into the local repository.

```xml
<!-- in the parent pom.xml -->
<modules>
    <module>module1</module>
    <module>module2</module>
    <module>module3</module>
</modules>
```

```xml
<!-- in the child pom.xml -->
<parent>
    <groupId>com.mycompany</groupId>
    <artifactId>myjavaproject</artifactId>
    <version>1.0-SNAPSHOT</version>
</parent>
```
