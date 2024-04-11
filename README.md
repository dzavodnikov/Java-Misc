# Java miscellaneous code

This code can be used as a template for other Java projects.

## Compile

Execute:

```sh
$ mvn clean package
```

## [FileSize](src/main/java/pro/zavodnikov/utils/FileSize.java)

Print file size in human-readable format:

```sh
3.7 GiB
2.0 KiB
2.9 MiB
4.5 TiB
1 Bytes
```

## [LogLevels](src/main/java/pro/zavodnikov/examples/LogLevels.java)

Example of [SLF4J](https://www.slf4j.org/) logging system. To run example execute:

```sh
$ mvn clean package
$ mvn exec:java -P slf4j-logback
$ mvn exec:java -P slf4j-log4j
```

## License

Distributed under MIT license.
