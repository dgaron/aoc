#!/bin/bash

g++ -o main -I.. main.cc ../readFile.cc -Wall -Wextra -Wpedantic -std=c++17

echo "I compiled your program, $USER"
