#include <iostream>
#include <vector>
#include <algorithm> // fill

#include "boboUtils.h"

using namespace std; // I do what I want

// Changed my mind
// void setBordersVisible(vector<vector<int> > &vis) {
//     int rows = vis.size();
//     int col = vis[0].size();

//     fill(vis[0].begin(), vis[0].end(), 1);
//     fill(vis[rows-1].begin(), vis[rows-1].end(), 1);
//     for (int i = 0; i < rows; ++i) {
//         vis[i][0] = 1;
//         vis[i][col-1] =1;
//     }
// }

int countVis(vector<vector<int> > &vis) {
    int sum = 0;
    for (vector<int> v : vis) {
        for (int n : v) {
            if (n == 1) {
                ++sum;
            }
        }
    }
    return sum;
}

void checkVisibility(const vector<vector<int> > &trees, vector<vector<int> > &vis) {
    int rows = trees.size();
    int cols = trees[0].size();

    // Ain't no Code Climate 'round here
    for (int i = 0; i < rows; ++i) {
        int currentMax = -1;
        for (int j = 0; j < cols; ++j) {
            if (trees[i][j] > currentMax) {
                vis[i][j] |= 1;
                currentMax = trees[i][j];
            }
        }
    }

    for (int i = 0; i < rows; ++i) {
        int currentMax = -1;
        for (int j = cols - 1; j >= 0; --j) {
            if (trees[i][j] > currentMax) {
                vis[i][j] |= 1;
                currentMax = trees[i][j];
            }
        }
    }

    for (int i = 0; i < cols; ++i) {
        int currentMax = -1;
        for (int j = 0; j < rows; ++j) {
            if (trees[j][i] > currentMax) {
                vis[j][i] |= 1;
                currentMax = trees[j][i];
            }
        }
    }

    for (int i = 0; i < cols; ++i) {
        int currentMax = -1;
        for (int j = rows - 1; j >= 0; --j) {
            if (trees[j][i] > currentMax) {
                vis[j][i] |= 1;
                currentMax = trees[j][i];
            }
        }
    }
}

int part1(const vector<vector<int> > &trees) {
    int rows = trees.size();
    int col = trees[0].size();

    vector<vector<int> > vis(rows, vector<int>(col, 0)); 

    // setBordersVisible(vis);

    checkVisibility(trees, vis);

    return countVis(vis);
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        cout << "Usage: " << argv[0] << " [filename]" << '\n';
        exit(1);
    }

    vector<vector<int> > trees = readFileInts(argv[1]);

    int visibleTrees = part1(trees);
    cout << "There are: " << visibleTrees << " visible trees" << '\n';

    return 0;
}