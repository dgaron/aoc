#include <fstream>
#include <cstdlib>
#include <cerrno>

#include "boboUtils.h"

using namespace std; // Deal with it

vector<string> readFile(const string &fileName) {
    ifstream in(fileName);
    string s;
    vector<string> data;
    while (getline(in, s)) {
        data.push_back(s);
    }
    return data;
}

long parseLong(const string &s) {
    const char *nptr = s.c_str();
    char *endptr = nullptr;
    long val = strtol(nptr, &endptr, 10);
    if (nptr == endptr || (errno == 0 && *endptr != 0)) {
        throw "Bad Conversion"s;
    } 
    return val;
}
