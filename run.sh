#!/bin/bash

set -e

mkdir -p bin lib

if [ ! -f "lib/junit-platform-console-standalone-1.9.2.jar" ]; then
    wget -q https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.9.2/junit-platform-console-standalone-1.9.2.jar -O lib/junit-platform-console-standalone-1.9.2.jar
fi

javac -d bin src/main/java/mylabs/app/*.java

case "${1:-main}" in
    main)
        java -cp bin mylabs.app.Main
        ;;
    test)
        javac -cp "bin:lib/*" -d bin src/test/java/mylabs/app/*.java
        java -cp "bin:lib/junit-platform-console-standalone-1.9.2.jar" org.junit.platform.console.ConsoleLauncher --scan-classpath
        ;;
    *)
        echo "Usage: ./run.sh [main|test]"
        ;;
esac
