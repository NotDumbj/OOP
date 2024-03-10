#include<iostream>
#include<string>
#include<vector>
using namespace std;


class Dog{
    public:
    string name;
    string breed;
    int age;


    void bark()
    {
        cout << endl << name << " says Woof! Woof!" << endl;
    }
    void displayDetails()
    {
        cout << "Pet Name: " << name << endl;
        cout << "Pet Breed: " << breed << endl;
        cout << "Pet Age: " << age << endl;
    }
    
};

class Person{
    public:
    Dog pet;
    Person() {}

    Person(string a, int n) : name (a) , age (n){
        cout << "Object Created" << endl;
    }
    ~Person()
    {
        cout << "Mission Successful" << endl;
    } 
    // pet code 
    void intoducepet()
    {
        cout << "\n" << name << " says:";
        cout << "\nI have a Dog, his name is " << pet.name << "\n";
    }
    // task 4
    void changepet(Dog new_pet)
    {
        pet = new_pet;
        cout << "\n" << name << " says:";
        cout << "\nCheck out my new pet, his name is " << pet.name << endl;
    }
    //task 4 end
    void displayDetails()
    {
        cout << "Name: " << name << endl;
        cout << "Age: " << age << endl;
        cout << "Dog: " << pet.name << endl;
    }
    private:
    int age;
    string name;
};

class House{
    public:
    House() {};
    string address;
    int inhabitants = 0;
    vector<Person> residents;

    void addResident(Person person) {
        residents.push_back(person);
        inhabitants++;
    }

    void displayResidentsDetails() 
    {
        cout << "Residents of the house at " << address << ":" << endl;
        for (auto &resident : residents) {
            resident.displayDetails();
            cout << endl;
        }
    }

    void displayDetails()
    {
        cout << "\nHouse Address: " << address << endl;
        cout << "Total Inhabitants: " << inhabitants << endl;
        displayResidentsDetails();
    }
};

//task 5
class Neighbourhood{
    public:
    Neighbourhood() {};

    Neighbourhood(string a) : name (a) {}

    ~Neighbourhood() {
        cout << name << " destroyed :" << endl;
    }
    string name;
    int noOfhouses;
    vector<House> homes;

    void addHouse(House home) {
        homes.push_back(home);
        noOfhouses++;
    }

    void displayHouseDetails() 
    {
        cout << "\nThe houses located in the " << name << ":" << endl;
        for (auto &house : homes) {
            house.displayDetails();
            cout << endl;
        }
    }
};
// task 5 end

int main()
{
    Neighbourhood neighbourhood1;
    neighbourhood1.name = "Mild Town";

    House house1;
    house1.address = "Mild Street 1";

    House house2;
    house2.address = "Mild Street 2";

    Person zohad("Zohad", 12);
    zohad.pet.name = "Tom";
    zohad.pet.breed = "german shepherd";
    zohad.pet.age = 7;
    zohad.displayDetails();

    Person jibran("Jibran", 18);
    jibran.pet.name = "Jerry";
    jibran.pet.breed = "husky";
    jibran.pet.age = 3;
    jibran.displayDetails();

    Person rafay("Rafay", 19);
    rafay.pet.name = "Hoppy";
    rafay.pet.breed = "golden retriever";
    rafay.pet.age = 5;
    rafay.displayDetails();

    Person hamza("Hamza" , 20);
    hamza.pet.name = "Zami";
    hamza.pet.breed = "husky";
    hamza.pet.age = 4;
    hamza.displayDetails();

    //zohad.intoducepet();
    //zohad.pet.bark();
    //zohad.pet.displayDetails();

    house1.addResident(zohad);
    house1.addResident(jibran);

    house2.addResident(rafay);
    house2.addResident(hamza);

    neighbourhood1.addHouse(house1);
    neighbourhood1.addHouse(house2);
    //house1.displayResidentsDetails();

// task 4
    Dog newpet;
    newpet.name = "Poppy";
    newpet.breed = "Chow Chow";
    newpet.age = 2;    
    jibran.changepet(newpet);
    jibran.pet.displayDetails();
// task 4 end

// task 5
    neighbourhood1.displayHouseDetails();
// task 5 end
    
    system("pause");
    return 0;
}