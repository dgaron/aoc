#!/bin/bash

g++ -o main main.cc readFile.cc -Wall -Wextra -Wpedantic -std=c++17

echo "I compiled your program, $USER"
