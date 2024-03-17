#include <iostream>
#include <list>

using namespace std;

class LinkedList {
public:
    list<int> linkedList;

    // Append a new element to the list
    void append(int data) {
        linkedList.push_back(data);
    }

    // Sort the list in ascending order
    void customSort() {
        linkedList.sort();
    }

    // Merge another list into this list
    void merge(LinkedList& otherList) {
        linkedList.merge(otherList.linkedList);
    }

    // Display the list
    void display() {
        for (const auto& elem : linkedList) {
            cout << elem << " ";
        }
        cout << endl;
    }
};

int main() {
    LinkedList list1;
    list1.append(3);
    list1.append(1);
    list1.append(5);

    LinkedList list2;
    list2.append(2);
    list2.append(4);
    list2.append(6);

    cout << "Original list 1: ";
    list1.display();
    cout << "Original list 2: ";
    list2.display();

    // Sort list 1
    list1.customSort();
    cout << "Sorted list 1: ";
    list1.display();

    // Merge list 2 into list 1
    list1.merge(list2);
    cout << "Merged list: ";
    list1.display();

    return 0;
}
