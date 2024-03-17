#include <iostream>
using namespace std;

class Vehicle 
{
    private:

    string make;
    string model;
    int year;

    public:

    bool service = false;
    int mileage = 0;

    Vehicle(string mk, string mdl, int yr): make(mk), model(mdl), year(yr) {}

    void setMake(string mk) 
    {
       make = mk;
    }

    void setModel(string mdl) 
    {
        model = mdl;
    }
    
    void setYear(int yr) 
    {
        year = yr;
    }

    void displayVehicleInfo() 
    {
        cout << "Make:" << make <<", Model: " << model <<", Year: " << year << endl;
    }

    void servicecheck()
    {
        if(mileage = 0)
        {
        cout << "Enter Cars Mileage : ";
        cin >> mileage;
        }
        if(mileage <= 10000)
        {
            service = false;
        }
        else if (mileage > 10000)
        {
            service = true;
        }
    }

    void servicemessage()
    {
        servicecheck();
        if(service = false)
        {
            cout << "You Car " << make << " " << model << " is Fit and Fine.";
        }
        else if(service = true)
        {
            cout << "Your Car " << make << " " << model << " Needs Servicing.";
        }
    }
};

int main()
{
    Vehicle myVehicle("Toyota","Corolla",2020);
    myVehicle.displayVehicleInfo();
    
    myVehicle.setModel("Prius");
    myVehicle.setYear(2022);

    myVehicle.displayVehicleInfo();

    myVehicle.mileage = 10254;

    myVehicle.servicemessage();
    
    return 0;
}