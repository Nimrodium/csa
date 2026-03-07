#!/usr/bin/env bash
CLASSES="."
MAIN="ReviewRunner"
javac -cp ${CLASSES} ${MAIN}.java && java ${MAIN}
