#!/usr/bin/env bash
CLASSES="."
MAIN="Main"
javac -cp ${CLASSES} ${MAIN}.java
java ${MAIN}