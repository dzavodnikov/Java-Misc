# Java miscellaneous code

This code can be used as a template for other Java projects.

Uses Java 11+.

## [utils](src/main/java/pro/zavodnikov/utils)

Contain following code:

-   [FileSize](src/main/java/pro/zavodnikov/utils/FileSize.java): human-readable file size.

## [LogExample](src/main/java/pro/zavodnikov/LogExample.java)

Example of [SLF4J](https://www.slf4j.org/) logging system. To run example execute:

```sh
    $ mvn exec:java -P slf4j-empty
    $ mvn exec:java -P slf4j-nop
    $ mvn exec:java -P slf4j-simple
    $ mvn exec:java -P slf4j-logback
```

## License

Distributed under MIT license.
