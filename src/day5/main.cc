#include <iostream>
#include <vector>
#include <string>
#include <stack>

#include "boboUtils.h"

using namespace std; // 😎

size_t whichStack(const size_t &found) {
    return (found / 4) + 1;
}


vector<stack<char> > dealWithThisNonsense(const vector<string> &data) {
    // 0 is just gonna be empty. I am not dealing with -1 everywhere.
    vector<stack<char> > stacks(10);
    for (int i = 7; i >= 0; --i) {
        const string &s = data[i];
        auto found = s.find('[');
        while (found != string::npos) {
            auto stack = whichStack(found);
            stacks[stack].push(s[found+1]);
            found = s.find('[', found+1);
        }
    }
    return stacks;
}

vector<int> parseMove(const string &s) {
    vector<string> tokens = tokenize(s);
    // Token 1 is num, 3 is source, 5 is target
    vector<int> coords;
    coords.push_back(parseLong(tokens[1]));
    coords.push_back(parseLong(tokens[3]));
    coords.push_back(parseLong(tokens[5]));
    return coords;
}

void moveCrates(const string &s, vector<stack<char> > &stacks) {
    vector<int> coords = parseMove(s);
    // Assignments so I remember
    int numCrates = coords[0];
    int source = coords[1];
    int target = coords[2];
    for (int i = 0; i < numCrates; ++i) {
        const char crate = stacks[source].top();
        stacks[source].pop();
        stacks[target].push(crate);
    }
}

void moveStacks(const string &s, vector<stack<char> > &stacks) {
    vector<int> coords = parseMove(s);                                                              
    // Assignments so I remember                                                                    
    int numCrates = coords[0];                                                                      
    int source = coords[1];                                                                         
    int target = coords[2];
    stack<char> movingCrates;  
    for (int i = 0; i < numCrates; ++i) { 
        const char crate = stacks[source].top();                                                   
        stacks[source].pop();
        movingCrates.push(crate);                                                            
    } 

    while (!movingCrates.empty()) {
        stacks[target].push(movingCrates.top());
        movingCrates.pop();
    } 
}

ostream &printStacks(ostream &os, const vector<stack<char> > stacks) {
    for (int i = 1; i < 10; ++i) {
        os << stacks[i].top();
    }
    return os;
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        cout << "Usage: " << argv[0] << " [filename]" << '\n';
        exit(1);
    }

    vector<string> data = readFile(argv[1]);

    // Strip off the stacks
    vector<stack<char> > stacks = dealWithThisNonsense(data);

    /* SEE TO BELIEVE
    for (auto v : stacks) {
        while(!v.empty()) {
            char c = v.top();
            v.pop();
            cout << c << ' ';
        }
        cout << '\n';
    }*/
    

    // Get the moves only
    vector<string> moves(data.begin()+10, data.end());

    for (const string &s : moves) {
        moveCrates(s, stacks);
    }

    cout << "PART 1 - Top Row: ";
    printStacks(cout, stacks);
    cout << '\n';

    // RESET THE STACKS DUHHHHH

    stacks = dealWithThisNonsense(data);

    for (const string &s : moves) {
        moveStacks(s, stacks);
    }    

    cout << "PART 2 - Top Row: ";
    for (int i = 1; i < 10; ++i) {                                                                  
        cout << stacks[i].top();                                                                   
    }                                                                                               
    cout << '\n';


    return 0;
}
