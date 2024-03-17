#include <list>
#include <iostream>

using namespace std;

class Spreadsheet {
private:
    list<list<int>> matrix;

public:
    // Constructor
    Spreadsheet(const list<list<int>>& initialMatrix) : matrix(initialMatrix) {}

    // Add a new row to the spreadsheet
    void addRow(const list<int>& newRow) {
        matrix.push_back(newRow);
    }

    // Delete a row from the spreadsheet
    void deleteRow(int rowIndex) {
        auto it = matrix.begin();
        advance(it, rowIndex);
        matrix.erase(it);
    }

    // Display the spreadsheet
    void display() const {
        for (const auto& row : matrix) {
            for (int val : row) cout << val << " ";
            cout << endl;
        }
    }
};

int main() {
    // Initialize a spreadsheet
    list<list<int>> initialMatrix = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };
    Spreadsheet mySpreadsheet(initialMatrix);

    // Display the initial spreadsheet
    cout << "Initial Spreadsheet:" << endl;
    mySpreadsheet.display();

    // Add a new row
    list<int> newRow = {10, 11, 12};
    mySpreadsheet.addRow(newRow);
    cout << "\nSpreadsheet after adding a new row:" << endl;
    mySpreadsheet.display();

    // Delete a row
    mySpreadsheet.deleteRow(1); // Deleting the second row
    cout << "\nSpreadsheet after deleting a row:" << endl;
    mySpreadsheet.display();

    return 0;
}
