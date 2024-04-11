# Java miscellaneous code

This code can be used as a template for other Java projects.

## Create own Maven project

### Add that parent file to you project

If you are create new Java-project:

1.  copy [base.xml](base.xml) to root of your project;
2.  create `pom.xml` that has a link to it:

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <project xmlns="http://maven.apache.org/POM/4.0.0"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
        <modelVersion>4.0.0</modelVersion>

        <parent>
            <groupId>org.zavodnikov</groupId>
            <artifactId>maven-base</artifactId>
            <version>1.0.0</version>
            <relativePath>./base.xml</relativePath>
        </parent>

        <groupId>...</groupId>
        <artifactId>...</artifactId>
        <version>...</version>
        <packaging>...</packaging>

        ...
    </project>
    ```

## [utils](src/main/java/pro/zavodnikov/utils)

### [FileSize](src/main/java/pro/zavodnikov/utils/FileSize.java)

Print file size in human-readable format:

```sh
3.7 GiB
2.0 KiB
2.9 MiB
4.5 TiB
1 Bytes
```

## License

Distributed under MIT license.
