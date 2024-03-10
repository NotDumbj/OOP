#include<iostream>
using namespace std;

class Plant{
    public:
    virtual void grow() = 0;
    virtual void displayHeight() const = 0;
};

class Flower : public Plant {
    private:
    double height; // in centimeters
    double growthRate; // cm per day
    public:
    Flower(double initialHeight) : height(initialHeight), growthRate(0.5) {}
    void grow() override {
        height += growthRate;
    }
    void displayHeight() const override {
        cout << "Flower height: " << height << " cm" << endl;
    }
};

class Tree : public Plant {
    private:
    double height; // in centimeters
    double growthRate; // cm per day
    public:
    Tree(double initialHeight) : height(initialHeight), growthRate(0.2) {}
    void grow() override {
        height += growthRate;
    }
    void displayHeight() const override {
        cout << "Tree height: " << height << " cm" << endl;
    }
};
class Bushes : public Plant {
    private:
    double height; // in centimeters
    double growthRate; // cm per day
    public:
    Bushes(double initialHeight) : height(initialHeight), growthRate(0.7) {}
    void grow() override {
        height += growthRate;
    }
    void displayHeight() const override {
        cout << "Bushes height: " << height << " cm" << endl;
    }
};



int main()
{
    // intialized all the plants 5 cm
    Flower flower1(5);
    Tree tree1(5);
    Bushes bushes1(5);
    // loop for 30 days
    for (int i = 0 ; i <= 30 ; i ++)
    {
        cout << "\nDay " << i << "\n";
        flower1.displayHeight();
        tree1.displayHeight();
        bushes1.displayHeight();
        flower1.grow();
        tree1.grow();
        bushes1.grow();
    }
    system("pause");
    return 0;
}