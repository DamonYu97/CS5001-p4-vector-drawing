#!/bin/bash
javac -sourcepath src -classpath out/production src/model/shape/*.java -d out/production
javac -sourcepath src -classpath out/production src/main/VectorDrawingMain.java -d out/production

javac -sourcepath test -classpath out/production:lib/junit4/junit.jar:lib/junit4/hamcrest-core.jar test/canvas_tests/CanvasTestSuiteRunner.java -d out/test
javac -sourcepath test -classpath out/production:lib/junit4/junit.jar:lib/junit4/hamcrest-core.jar test/shape_tests/ShapeTestSuiteRunner.java -d out/test
javac -sourcepath test -classpath out/production:lib/junit4/junit.jar:lib/junit4/hamcrest-core.jar test/util_tests/UtilTestSuiteRunner.java -d out/test

java -classpath out/production:out/test:lib/junit4/junit.jar:lib/junit4/hamcrest-core.jar util_tests.UtilTestSuiteRunner
java -classpath out/production:out/test:lib/junit4/junit.jar:lib/junit4/hamcrest-core.jar shape_tests.ShapeTestSuiteRunner
java -classpath out/production:out/test:lib/junit4/junit.jar:lib/junit4/hamcrest-core.jar canvas_tests.CanvasTestSuiteRunner
