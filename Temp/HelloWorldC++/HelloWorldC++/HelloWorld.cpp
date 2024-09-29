/*
Name:		Janice Manning
Date:		March, 2019
Purpose:	To introduce simple io
*/

#include <iostream>
#include <string>

//the C++ Standard Library has a defined namespace called std used to declare identifiers
//to use cout, cin, endl you must the std specifier like this std::cout
//to avoid having to use the std:: qualification, type the following using directive
using namespace std;			

//function declarations
bool isOdd(unsigned age);
void tripleMe(unsigned & age);

int main()
{
	//1. output to console
	//use of insertion operator to insert data to cout
	std::cout << "Hello World\n";
	cout << "Hello World" << endl;
	std::cout << "\tHello World\n";

	//Literal data
	cout << "Sum = " << 2 + 8 << endl;

	//2. Input from keyboard
	//use of extraction operator to extract data from cin
	unsigned width, length, height, surfaceArea;
	cout << "Enter a width, length, height:\n";
	cin >> width >> length >> height;
	surfaceArea = 2 * width + 2 * length + 2 * height;
	cout << "Surface area = " << surfaceArea << endl;

	//3. >> operator is an overloaded operator function, permitting formatted input of all built-in types
	//all built-in types are overloaded in the iostream library
	//any other library has also overloaded the >> function
	cout << "Enter your name: ";
	string name;
	cin >> name;
	cout << "Hello, " << name << endl;

	//4. function	
	//is your age an odd number? passing age by value
	cout << "Enter your age: ";
	unsigned age;
	cin >> age;
	if (isOdd(age))
		cout << name << ", your age is an odd number\n";
	else
		cout << name << ", your age is an even number\n";

	//triple your age - passing age by reference
	tripleMe(age);
	cout << name << ", your age tripled = " << age << endl;
	
	//5. introducing C++ reference variable
	unsigned & myAge = age;	//must be assigned its variable address when declared
	
	//unlike a pointer, which can be assigned many variable addresses throughout a program
	unsigned * ptr;
	ptr = &age;
	ptr = &width;
	ptr = &length;

	return 0;	//usually omitted
}

//function definition
void tripleMe(unsigned & age)
{
	age *= 3;
}

//function definition
bool isOdd(unsigned age)
{
	return (age % 2 != 0) ? true : false;
}
