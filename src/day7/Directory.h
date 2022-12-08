#ifndef DIRECTORY_H
#define DIRECTORY_H

#include <list>
#include <string>

class Directory {

  private:

    Directory *parent;

    const std::string name;

    std::list<Directory *> children;

    long size;

    void freeTheChildren();
    
    void updateParentSize(long);

  public:

    Directory() = delete;

    Directory(Directory *, const std::string);

    ~Directory();

    void addChild(const std::string);

    const std::string &getName() const;

    long getSize() const;

    void updateSize(long);

    Directory *getParent() const;

    Directory *findChild(const std::string &) const;
};

#endif // DIRECTORY_H