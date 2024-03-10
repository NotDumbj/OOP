#include <iostream>
#include <map>
#include <string>

using namespace std;

class ContactManager {
private:
    map<string, string> contacts;

public:
    void addContact(const string& name, const string& phoneNumber) {
        contacts[name] = phoneNumber;
        cout << "Contact added: " << name << " - " << phoneNumber << endl;
    }

    void searchContact(const string& name) {
        auto it = contacts.find(name);
        if (it != contacts.end()) {
            cout << "Contact found: " << it->first << " - " << it->second << endl;
        } else {
            cout << "Contact not found for name: " << name << endl;
        }
    }

    void displayAllContacts() {
        if (contacts.empty()) {
            cout << "No contacts available." << endl;
        } else {
            cout << "All contacts:" << endl;
            for (const auto& pair : contacts) {
                cout << pair.first << " - " << pair.second << endl;
            }
        }
    }
};

int main() {
    ContactManager contactManager;

    contactManager.addContact("Jibran", "1234567890");
    contactManager.addContact("Hamza", "9876543210");
    contactManager.addContact("Zohad", "1112223333");

    contactManager.searchContact("Jibran");
    contactManager.searchContact("Charlie");

    contactManager.displayAllContacts();

    return 0;
}
