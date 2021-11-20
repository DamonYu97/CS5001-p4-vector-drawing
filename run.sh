#!/bin/bash
javac -sourcepath src -classpath out/production src/model/shape/*.java -d out/production
javac -sourcepath src -classpath out/production src/main/VectorDrawingMain.java -d out/production
java -classpath out/production main.VectorDrawingMain