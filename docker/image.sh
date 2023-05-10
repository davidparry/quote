#!/bin/sh

cp ~/code/github/quote/build/native/nativeCompile/quote .
docker build -t quote .
rm quote
