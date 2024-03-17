#include <iostream>
#include <list>
#include <string>

using namespace std;

class ToDoList {
private:
    string task;
    list<ToDoList> subTasks;

public:
    // Constructor
    ToDoList(const string& taskName) : task(taskName) {}

    // Add a sub-task
    void addSubTask(const string& subTaskName) {
        subTasks.push_back(ToDoList(subTaskName));
    }

    // Get reference to subTasks list
    list<ToDoList>& getSubTasks() {
        return subTasks;
    }

    // Display the task and its sub-tasks recursively
    void display(int depth = 0) const {
        cout << string(depth, '\t') << "- " << task << endl;
        for (const auto& subTask : subTasks) {
            subTask.display(depth + 1);
        }
    }
};

int main() {
    // Create a ToDoList
    ToDoList mainTask("Main Task");

    // Add sub-tasks
    mainTask.addSubTask("Sub-task 1");
    mainTask.addSubTask("Sub-task 2");
    mainTask.addSubTask("Sub-task 3");

    // Add sub-tasks to Sub-task 1
    mainTask.getSubTasks().front().addSubTask("Sub-task 1.1");
    mainTask.getSubTasks().front().addSubTask("Sub-task 1.2");

    // Add sub-tasks to Sub-task 2
    mainTask.getSubTasks().back().addSubTask("Sub-task 2.1");

    // Display the ToDoList
    cout << "To-Do List:" << endl;
    mainTask.display();

    return 0;
}
