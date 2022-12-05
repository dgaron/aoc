#include <fstream>

#include "readFile.h"

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
