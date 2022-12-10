#include <iostream>
#include <vector>
#include <algorithm> // fill, max

#include "boboUtils.h"

using namespace std; // I do what I want


void setBorders(vector<vector<int> > &vis, int val) {
    int rows = vis.size();
    int col = vis[0].size();

    fill(vis[0].begin(), vis[0].end(), val);
    fill(vis[rows-1].begin(), vis[rows-1].end(), val);
    for (int i = 0; i < rows; ++i) {
        vis[i][0] = val;
        vis[i][col-1] = val;
    }
}

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

    setBorders(vis, 1);

    checkVisibility(trees, vis);

    return countVis(vis);
}

int getScore(const vector<vector<int> > &trees, int row, int col) {
    int numRows = trees.size();
    int numCols = trees[0].size();

    // It's gross
    int right = 0;
    for (int i = col + 1; i < numCols; ++i) {
         ++right;
        if (trees[row][col] <= trees[row][i]) {
            break;
        }
    }

    int left = 0;
    for (int i = col - 1; i > -1; --i) {
         ++left;
        if (trees[row][col] <= trees[row][i]) {
            break;
        }
    }


    int down = 0;
    for (int i = row + 1; i < numRows; ++i) {
         ++down;
        if (trees[row][col] <= trees[i][col]) {
            break;
        }
    }

    int up = 0;
    for (int i = row - 1; i > -1; --i) {
         ++up;
        if (trees[row][col] <= trees[i][col]) {
            break;
        }
    }

    return right * left * up * down;
}

int calculateScenicScores(const vector<vector<int> > &trees, vector<vector<int> >&scores) {
    int rows = trees.size();
    int col = trees[0].size();

    int maxScore = 0;

    for (int i = 1; i < rows - 1; ++i) {
        for (int j = 1; j < col - 1; ++j) {
            scores[i][j] = getScore(trees, i, j);
            maxScore = max(maxScore, scores[i][j]);
        }
    }

    return maxScore;
}

int part2(const vector<vector<int> > &trees) {
    int rows = trees.size();
    int col = trees[0].size();

    vector<vector<int> > scores(rows, vector<int>(col, 0)); 

    setBorders(scores, 0);

    int maxScore = calculateScenicScores(trees, scores);

    return maxScore;
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        cout << "Usage: " << argv[0] << " [filename]" << '\n';
        exit(1);
    }

    vector<vector<int> > trees = readFileInts(argv[1]);

    int visibleTrees = part1(trees);
    cout << "There are: " << visibleTrees << " visible trees" << '\n';

    int bestTreeScore = part2(trees);
    cout << "The highest scenic score is: " << bestTreeScore << '\n';

    return 0;
}