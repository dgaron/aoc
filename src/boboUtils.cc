#include <fstream>
#include <sstream>  // istringstream
#include <cstdlib>  // strtol
#include <cerrno>   // errno 

#include "boboUtils.h"

using namespace std; // Deal with it

vector<string> readFile(const string &fileName) {
    ifstream in(fileName);
    string s;
    vector<string> data;
    while (getline(in, s)) {
        data.push_back(s);
    }
    in.close();
    return data;
}

long parseLong(const string &s) {
    const char *nptr = s.c_str();
    char *endptr = nullptr;
    long val = strtol(nptr, &endptr, 10);
    if (nptr == endptr || (errno == 0 && *endptr != 0)) {
        throw "a tantrum";
    } 
    return val;
}

vector<string> tokenize(const string &s, const char delim) {
    vector<string> tokens;
    string token;
    istringstream iss(s);
    while (getline(iss, token, delim)) {
        tokens.push_back(token);
    }
    return tokens;
}

// Originally for Day 8
vector<vector<int> > readFileInts(const string &fileName) {
    ifstream in(fileName);
    vector<vector<int> > data;
    string s;
    int row = 0;
    while (getline(in, s)) {
        data.push_back(vector<int>());
        for (auto c : s) {
            int n = c - 48; // ASCII Magic
            data[row].push_back(n);
        }
        ++row;
    }
    in.close();
    return data;
}
