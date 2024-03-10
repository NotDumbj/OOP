#include <iostream>
#include <vector>
using namespace std;

class Task {
public:
    string description;
    bool completed;
    Task(string desc) : description(desc), completed(false) {}
};

class ToDoList {
    vector<Task*> tasks;
public:
    void addTask(const string& desc) { tasks.push_back(new Task(desc)); }
    
    void listTasks() {
        if (tasks.empty()) {
            cout << "No tasks in the list." << endl;
            return;
        }
        cout << "Tasks in the list:" << endl;
        for (size_t i = 0; i < tasks.size(); ++i) {
            cout << i + 1 << ". ";
            if (tasks[i]->completed) {
                cout << "[Completed] ";
            }
            cout << tasks[i]->description << endl;
        }
    }
    
    void markTaskCompleted(size_t index) {
        if (index >= 1 && index <= tasks.size()) {
            tasks[index - 1]->completed = true;
            cout << "Task marked as completed: " << tasks[index - 1]->description << endl;
        } else {
            cout << "Invalid task index." << endl;
        }
    }
    
    ~ToDoList() { 
        for (auto &task : tasks) 
            delete task; 
    }
};

int main() {
    ToDoList myList;
    myList.addTask("Finish Lab 3");
    myList.addTask("Submit Assignment");
    
    myList.listTasks();
    
    myList.markTaskCompleted(1);
    
    myList.listTasks();
    
    return 0;
}
