#!/bin/bash

if g++ -o main -I.. main.cc ../boboUtils.cc -Wall -Wextra -Wpedantic -std=c++17; then
    echo "I compiled your program, $USER"
else
    echo "Get your shit together"
fi
