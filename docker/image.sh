#!/bin/sh

cp ~/code/github/quote/build/native/nativeCompile/chuck .
docker build -t quote .
rm quote
