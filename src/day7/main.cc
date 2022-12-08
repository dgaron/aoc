#include <iostream>
#include <vector>
#include <list>
#include <string>
#include <cctype> // isdigit

#include "boboUtils.h"
#include "Directory.h"

using namespace std; // lol

long sumDirectoriesUnder100k(const list<Directory *> &fileTree) {
    long sum = 0;
    for (Directory *d : fileTree) {
        long dirSize = d->getSize();
        if (dirSize <= 100000) {
            sum += dirSize;
        }
    }
    return sum;
}

void buildFileTree(list<Directory *> &fileTree, const vector<string> &data) {
    Directory *currentDir = fileTree.front();
    for (size_t i = 1; i < data.size(); ++i) {
        vector<string> tokens = tokenize(data[i]);

        // Ignore ls
        if (data[i].substr(0,4) == "$ ls") {
            continue;
        }

        // Add stuff up
        if (tokens[0] == "dir") {
            currentDir->addChild(tokens[1]);
            fileTree.push_back(currentDir->findChild(tokens[1]));
        } else if (tokens[0] != "$" && isdigit(tokens[0][0])) {
            currentDir->updateSize(parseLong(tokens[0]));
        }

        // Move around
        if (data[i].substr(0,4) == "$ cd" && tokens[2] == "..") {
            currentDir = currentDir->getParent();
        } else if (data[i].substr(0,4) == "$ cd") {
            currentDir = currentDir->findChild(tokens[2]);
        }

    }
}

long part1(const vector<string> &data) {
    Directory root(nullptr, "\\");
    list<Directory *> fileTree;
    fileTree.push_back(&root);
    buildFileTree(fileTree, data);
    long sum = sumDirectoriesUnder100k(fileTree);
    return sum;
}


int main(int argc, char *argv[]) {
    if (argc != 2) {
        cout << "Usage: " << argv[0] << " [filename]" << '\n';
        exit(1);
    }

    vector<string> data = readFile(argv[1]);

    long sum = part1(data);

    cout << "Sum of directories under 100k: " << sum << '\n';

}