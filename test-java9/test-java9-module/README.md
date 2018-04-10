# build text module
```shell
javac -d text/classes $(find text/src -name '*.java')

jar --create --file libs/text.jar -C text/classes .
```

# build hello main
```shell
javac -d hello/classes -modulepath text/target $(find hello/src -name '*.java')

jar --create --file libs/hello.jar --main-class org.harmony.java9.hello.Main -C hello/classes .
```

# run hello module
```shell
java -modulepath libs -m org.harmony.java9.hello
```

|open jdk| oracle jdk|
|:-      |:-         |
|-modulepath|--module-path|

