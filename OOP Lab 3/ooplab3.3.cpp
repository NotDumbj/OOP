#include <iostream>
#include <vector>
#include <string>
using namespace std;

struct Student {
    int id;
    string name;
    float grade;
    Student(int i, string n, float g) : id(i), name(n), grade(g) {}
};

class StudentManager {
private:
    vector<Student*> records;
public:
    void addStudent(int id, string name, float grade) {
        Student* newStudent = new Student(id, name, grade);
        records.push_back(newStudent);
    }
    
    void updateGrade(int id, float newGrade) {
        for (Student* student : records) {
            if (student->id == id) {
                student->grade = newGrade;
                return;
            }
        }
        cout << "Student ID not found." << endl;
    }
    
    void displayRecords() {
        if (records.empty()) {
            cout << "No student records available." << endl;
            return;
        }
        cout << "Student Records:" << endl;
        for (Student* student : records) {
            cout << "ID: " << student->id << ", Name: " << student->name << ", Grade: " << student->grade << endl;
        }
    }
    
    ~StudentManager() {
        for (Student* student : records) {
            delete student;
        }
    }
};

int main() {
    StudentManager sm;
    
    sm.addStudent(1, "John Doe", 92.5);
    sm.addStudent(2, "Jane Doe", 88.0);
    
    cout << "Initial Student Records:" << endl;
    sm.displayRecords();
    
    sm.updateGrade(1, 95.0);
    
    cout << "\nStudent Records after Grade Update:" << endl;
    sm.displayRecords();

    return 0;
}
