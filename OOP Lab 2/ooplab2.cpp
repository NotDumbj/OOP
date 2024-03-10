#include<iostream>
using namespace std;

class Animal{
    public:
    virtual void makeSound() = 0;
    void hearsound(){
        makeSound();
    }
};

class Lion : public Animal {
    public:
    void makeSound() override{
        cout << "\ngrraaaauuuu!\n";
    }
};

class Elephant : public Animal {
    public:
    void makeSound() override{
        cout << "\nPawoo!\n";
    }
};

class Monkey : public Animal {
    public:
    void makeSound() override{
        cout << "\nhoo hoo hah ooh ha\n";
    }
};

class Bird : public Animal {
    public:
    void makeSound() override{
        cout << "\nchirp chirp\n";
    }
};




int main()
{
    Lion lion1;
    lion1.hearsound();
    Elephant elephant1;
    elephant1.hearsound();
    Monkey monkey1;
    monkey1.hearsound();
    Bird sparrow;
    sparrow.hearsound();
    return 0;
}