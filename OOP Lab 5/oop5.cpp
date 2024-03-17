#include <iostream>
#include <vector>
#include <algorithm> // for std::find

using namespace std;

class Book {
public:
    // Constructor
    Book(const string& title, const string& author, int id) : title(title), author(author), id(id) {}

    // Getters
    string getTitle() const { return title; }
    string getAuthor() const { return author; }
    int getID() const { return id; }

    // Display details
    void displayDetails() const {
        cout << "Title: " << title << ", Author: " << author << ", ID: " << id << endl;
    }

private:
    string title;
    string author;
    int id;
};

class Library {
public:
    // Add a book to the library
    void addBook(const Book& book) {
        books.push_back(book);
        cout << "Added '" << book.getTitle() << "' to the library." << endl;
    }

    // Remove a book from the library
    void removeBook(int id) {
        auto it = find_if(books.begin(), books.end(), [id](const Book& book) {
            return book.getID() == id;
        });
        if (it != books.end()) {
            cout << "Removed '" << it->getTitle() << "' from the library." << endl;
            books.erase(it);
        } else {
            cout << "Book with ID " << id << " not found in the library." << endl;
        }
    }

    // List all books in the library
    void listBooks() const {
        for (const auto& book : books) {
            book.displayDetails();
        }
    }

private:
    vector<Book> books;
};

void userInterface(Library& lib) {
    string command;
    while (true) {
        cout << "Enter command (add, remove, list, quit): ";
        cin >> command;

        if (command == "add") {
            string title, author;
            int id;
            cout << "Enter title, author, and ID: ";
            cin >> title >> author >> id;
            lib.addBook(Book(title, author, id));
        } else if (command == "remove") {
            int id;
            cout << "Enter ID of the book to remove: ";
            cin >> id;
            lib.removeBook(id);
        } else if (command == "list") {
            cout << "Books in the library:" << endl;
            lib.listBooks();
        } else if (command == "quit") {
            break;
        } else {
            cout << "Invalid command. Please try again." << endl;
        }
    }
}

int main() {
    Library myLibrary;
    userInterface(myLibrary);
    return 0;
}
