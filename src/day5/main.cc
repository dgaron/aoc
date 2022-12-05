#include <iostream>
#include <vector>
#include <string>
#include <sstream>
#include <stack>

#include "readFile.h"

using namespace std; // 😎

size_t whichCrate(const size_t &found) {
    return (found / 4) + 1;
}


vector<stack<char> > dealWithThisNonsense(const vector<string> &data) {
    // 0 is just gonna be empty. I am not dealing with -1 everywhere.
    vector<stack<char> > crates(10);
    for (int i = 7; i >= 0; --i) {
        const string &s = data[i];
        auto found = s.find('[');
        while (found != string::npos) {
            auto crate = whichCrate(found);
            crates[crate].push(s[found+1]);
            found = s.find('[', found+1);
        }
    }
    return crates;
}

vector<int> parseMove(const string &s) {
    vector<string> tokens;
    string token;
    istringstream iss(s);
    while (getline(iss, token, ' ')) {
        tokens.push_back(token);
    }
    // Token 1 is num, 3 is source, 5 is target
    vector<int> coords;
    coords.push_back(stoi(tokens[1]));
    coords.push_back(stoi(tokens[3]));
    coords.push_back(stoi(tokens[5]));
    return coords;
}

void moveCrates(const string &s, vector<stack<char> > &crates) {
    vector<int> coords = parseMove(s);
    // Assignments so I remember
    int numCrates = coords[0];
    int source = coords[1];
    int target = coords[2];
    for (int i = 0; i < numCrates; ++i) {
        const char crate = crates[source].top();
        crates[source].pop();
        crates[target].push(crate);
    }
}

void moveStacks(const string &s, vector<stack<char> > &crates) {
    vector<int> coords = parseMove(s);                                                              
    // Assignments so I remember                                                                    
    int numCrates = coords[0];                                                                      
    int source = coords[1];                                                                         
    int target = coords[2];
    stack<char> movingCrates;  
    for (int i = 0; i < numCrates; ++i) { 
        const char crate = crates[source].top();                                                   
        crates[source].pop();
        movingCrates.push(crate);                                                            
    } 
    for (int i = 0; i < numCrates; ++i) {
        crates[target].push(movingCrates.top());
        movingCrates.pop();
    }
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        cout << "Usage: " << argv[0] << " [filename]" << '\n';
        exit(1);
    }

    vector<string> data = readFile(argv[1]);

    // Strip off the crates
    vector<stack<char> > crates = dealWithThisNonsense(data);

    /* SEE TO BELIEVE
    for (auto v : crates) {
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
        moveCrates(s, crates);
    }

    cout << "PART 1 - Top Row: ";
    for (int i = 1; i < 10; ++i) {
        cout << crates[i].top();
    }
    cout << '\n';

    // RESET THE CRATES DUHHHHH
    crates = dealWithThisNonsense(data);

    for (const string &s : moves) {
        moveStacks(s, crates);
    }    

    cout << "PART 2 - Top Row: ";
    for (int i = 1; i < 10; ++i) {                                                                  
        cout << crates[i].top();                                                                   
    }                                                                                               
    cout << '\n';


    return 0;
}
