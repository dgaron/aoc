#include <iostream>
#include <vector>
#include <string>
#include <cctype>

#include "boboUtils.h"

using namespace std; // lol

int main(int argc, char *argv[]) {
    if (argc != 2) {
        cout << "Usage: " << argv[0] << " [filename]" << '\n';
        exit(1);
    }

    vector<string> data = readFile(argv[1]);

    
}