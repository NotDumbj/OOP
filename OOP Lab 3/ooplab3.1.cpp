#include<iostream>
#include<vector>
using namespace std;

class Grade
{
private:
    char grade;

public:
    Grade(string n, char g) : subject_name(n), grade(g) {}

    string subject_name;
   
    string displaygrade() const
    {
        return subject_name + " = " + grade;
    }

};

class Student
{
private:
    string student_name;

public:
    Student(string n) : student_name(n) {}

    vector<Grade> grades1;

    void addgrade(Grade grades)
    {
        grades1.push_back(grades);
    }

    void displaygrades()
    {
        if (grades1.empty())
        {
            cout << "\nNo Grade Available\n";
        }
        else
        {
            cout << endl << student_name << "'s Grade:\n";
            for (const auto &grade : grades1)
            {
                cout << grade.displaygrade() << endl;
            }
        }
    }

    void removegrade(string gradename)
    {
        int vectorerasecounter = 0;
        for (const auto &grade : grades1)
        {
            if (gradename == grade.subject_name)
            {
                grades1.erase(grades1.begin() + vectorerasecounter);
            }
            vectorerasecounter++;
        }
    }
};

int main()
{
    Student student("Jibran");
    student.addgrade(Grade("OOP", 'A'));
    student.addgrade(Grade("Linear Algebra", 'B'));
    student.addgrade(Grade("ISE", 'A'));
    student.addgrade(Grade("Discrete Structures", 'B'));
    student.addgrade(Grade("Islamic Studies", 'A'));
    student.displaygrades();
    student.removegrade("Discrete Structures");
    student.displaygrades();

    return 0;
}
