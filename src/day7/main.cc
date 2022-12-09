#include <iostream>
#include <vector>
#include <list>
#include <string>
#include <cctype> // isdigit

#include "boboUtils.h"
#include "Directory.h"

using namespace std; // lol

long sumDirectoriesUnder100k(const list<Directory *> &dirList) {
    long sum = 0;
    for (Directory *d : dirList) {
        long dirSize = d->getSize();
        if (dirSize <= 100000) {
            sum += dirSize;
        }
    }
    return sum;
}

void buildFileTree(list<Directory *> &dirList, const vector<string> &data) {
    Directory *currentDir = dirList.front();
    for (size_t i = 1; i < data.size(); ++i) {
        vector<string> tokens = tokenize(data[i]);

        // Ignore ls
        if (data[i].substr(0,4) == "$ ls") {
            continue;
        }

        // Add stuff up
        if (tokens[0] == "dir") {
            currentDir->addChild(tokens[1]);
            Directory *newChild = &(currentDir->getChild(tokens[1]));
            dirList.push_back(newChild);
        } else if (tokens[0] != "$" && isdigit(tokens[0][0])) {
            currentDir->updateSize(parseLong(tokens[0]));
        }

        // Move around
        if (data[i].substr(0,4) == "$ cd" && tokens[2] == "..") {
            currentDir = currentDir->getParent();
        } else if (data[i].substr(0,4) == "$ cd") {
            currentDir = &(currentDir->getChild(tokens[2]));
        }
    }
}

bool compareSmallestFirst(Directory *first, Directory *second) {
    long firstSize = first->getSize();
    long secondSize = second->getSize();
    return (firstSize < secondSize);
}


long findDirSize(list<Directory *> &dirList, int driveSize, int spaceNeeded) {
    long freeSpace = driveSize - dirList.front()->getSize();
    long spaceToClear = spaceNeeded - freeSpace;
    cout << "Free Space: " << freeSpace << " CLEAR: " << spaceToClear << '\n';
    if (spaceToClear <= 0) {
        return 0;
    }
    dirList.sort(compareSmallestFirst);
    long dirSize = 0;
    for (Directory *d : dirList) {
        dirSize = d->getSize();
        if (dirSize >= spaceToClear) {
            return dirSize;
        }
    }
    return -1;
}

long part2(list<Directory *> &dirList) {
    const int TOTAL_SIZE = 70'000'000;
    const int SPACE_NEEDED = 30'000'000;

    long dirSize = findDirSize(dirList, TOTAL_SIZE, SPACE_NEEDED);
    return dirSize;
}


int main(int argc, char *argv[]) {
    if (argc != 2) {
        cout << "Usage: " << argv[0] << " [filename]" << '\n';
        exit(1);
    }

    vector<string> data = readFile(argv[1]);

    Directory root(nullptr, "\\");
    list<Directory *> dirList;
    dirList.push_back(&root);
    buildFileTree(dirList, data);

    long part1Sum = sumDirectoriesUnder100k(dirList);
    cout << "Sum of directories under 100k: " << part1Sum << '\n';

    long dirSize = part2(dirList);
    cout << "Directory size to be deleted: " << dirSize << '\n';

}
