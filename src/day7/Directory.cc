#include "Directory.h"

#include <iostream>

using namespace std;

Directory::Directory(Directory *parent, const string name) : parent(parent), name(name)  {
    size = 0;
}

Directory::~Directory() {
    //freeTheChildren();
}

void Directory::freeTheChildren() {
    if (children.empty()) {
        return;
    }
    for (Directory *child : children) {
        child->freeTheChildren();
        // delete(child);
    }
}

void Directory::addChild(const string name) {
    Directory *newChild = new Directory(this, name);
    children.push_back(newChild);
}

const std::string &Directory::getName() const {
    return name;
}

long Directory::getSize() const {
    return size;
}

void Directory::updateSize(long fileSize) {
    size += fileSize;
    updateParentSize(fileSize);
}

void Directory::updateParentSize(long fileSize) {
    if (parent != nullptr) {
        parent->updateSize(fileSize);
    }
}

Directory *Directory::findChild(const std::string &name) const {
    for (Directory *child : children) {
        if (child->getName() == name) {
            return child;
        }
    }
    throw "Oh fuck";
}

Directory *Directory::getParent() const {
    return this->parent;
}
