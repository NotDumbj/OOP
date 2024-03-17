#include <iostream>

class Node {
public:
    int data;
    Node* next;

    Node(int d) : data(d), next(nullptr) {}
};

class LinkedList {
public:
    Node* head;

    LinkedList() : head(nullptr) {}

    // Append a new node to the end of the list
    void append(int data) {
        Node* newNode = new Node(data);
        if (!head) {
            head = newNode;
        } else {
            Node* temp = head;
            while (temp->next) {
                temp = temp->next;
            }
            temp->next = newNode;
        }
    }

    // Search for a value in the list
    bool search(int value) {
        Node* current = head;
        while (current) {
            if (current->data == value) {
                return true;
            }
            current = current->next;
        }
        return false;
    }

    // Reverse the list
    void reverse() {
        Node* prev = nullptr;
        Node* current = head;
        Node* next = nullptr;
        while (current) {
            next = current->next;
            current->next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    // Display the list
    void display() {
        Node* temp = head;
        while (temp) {
            std::cout << temp->data << " ";
            temp = temp->next;
        }
        std::cout << std::endl;
    }
};

int main() {
    LinkedList list;

    list.append(1);
    list.append(2);
    list.append(3);
    list.append(4);

    std::cout << "Original list: ";
    list.display();

    int searchValue = 3;
    if (list.search(searchValue)) {
        std::cout << searchValue << " found in the list." << std::endl;
    } else {
        std::cout << searchValue << " not found in the list." << std::endl;
    }

    list.reverse();
    std::cout << "Reversed list: ";
    list.display();

    return 0;
}
