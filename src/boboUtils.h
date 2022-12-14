#ifndef BOBO_UTILS_H
#define BOBO_UTILS_H

#include <vector>
#include <string>

std::vector<std::string> readFile(const std::string &fileName);

long parseLong(const std::string &s);

std::vector<std::string> split(const std::string &s, const char delim = ' ');

std::vector<std::vector<int> > readFileInts(const std::string &fileName);

#endif // BOBO_UTILS_H
