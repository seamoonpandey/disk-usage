/*
Janice Manning
To introduce use c-strings (compile-time char arrays)
some <string.h> functions
January, 2023
*/


//#define _CRT_SECURE_NO_WARNINGS	
#include <stdio.h>
#include <ctype.h>
#include <string.h>

void addChars(char str[])
{
	int i = 0;
	str[i++] = 'A';
	str[i++] = 'B';
	str[i++] = 'C';
	//str[i] = '\0';

}

int main()
{
	//1. Some C-string declarations
	//You can refer to these as:  C-string, string, char array
	//once declared memory is allocated and size is fixed
	char str1[10];							//capacity = 10
	char str2[8] = "Hello";					//capacity = 8, '\0' appended
	char str3[] = { 'W','o','r','l','d' };	//capacity = 5, '\0' not appended
	char str4[] = "My World";				//capacity = 9, '\0' appended
	
	size_t size = strlen(str4);
	size_t capacity = sizeof(str4);
		
	//2. Recall printf requires %s to print a C-string
	str1[0] = 'H';
	str1[1] = 'i';
	//strlen looks for the terminating character, if not there returns undefined results
	size = strlen(str1);

	//printf looks for the terminating character
	printf("str1 = %s\n", str1);	//prints "Hi" followed by undefined results
	printf("str2 = %s\n", str2);	//prints Hello, because \0 has been appended
	printf("str3 = %s\n", str3);	//prints World, followed by undefined results
	printf("str4 = %s\n", str4);	//prints My World, because \0 has been appended

	//3. strings cannot be reinitialized
	//str4 = "ABC";
	// strings can be modified like an array by accessing char elements
	str4[0] = 'A';
	printf("%s\n", str4);
	str4[5] = '\0';
	printf("%s\n", str4);

	//4. Read from stdin
	printf("Enter a word: ");
	char input[8];
	(void)scanf_s("%s", input, 8);	// here we cannot overflow the buffer
	printf("Input = %s\n", input);
	
	// advance stdin pointer because any leftover input will still be in buffer
	for (int ch; (ch = getchar()) != EOF && ch != '\n'; );

	// read limited
	printf("try again: ");
	(void)scanf_s("%7s", input, 8);	// here we control the buffer flow
	printf("Input = %s\n", input);

	// advance stdin pointer because any leftover input will still be in buffer
	for (int ch; (ch = getchar()) != EOF && ch != '\n'; );
		
	//5. Some <string.h> functions
#define BUFFER_SIZE 200u	
	char word[BUFFER_SIZE + 1] = { 0 };
	printf("Enter a word: ");
	(void)scanf_s("%s", word, BUFFER_SIZE);

	//copy strings, making sure destination has buffer space
	//copy(destination, buffer, source)
	char copy[BUFFER_SIZE + 1] = { 0 };
	strcpy_s(copy, BUFFER_SIZE, word);
	
	//concatenate strings, making sure destination has buffer space
	//strcat_s(destination, buffer, source)
	strcat_s(word, BUFFER_SIZE, " with this ");
	strcat_s(word, BUFFER_SIZE,  "string ");
	strcat_s(word, BUFFER_SIZE, "concatenated");
	puts(word);

	//compare two strings, character by character, lexicographically
	//if return <0, lower value in ASCII, alpha higher
	//if return >0, higher value in ASCII, alpha lower
	//if return 0, equal
	char text[] = "abcde";
	char next[] = "ahijk";
	
	if (strcmp(text, next) < 0)
		printf("%s comes before %s\n", text, next);
	else if(strcmp(text, next) > 0)
		printf("%s comes after %s\n", text, next);
	else
		puts("String match");

	//6. A user-defined function
	//char another[BUFFER_SIZE];
	char another[BUFFER_SIZE] = { '\0' };  //the fix
	addChars(another);
	printf("%s\n", another);

	addChars(next);
	printf("%s\n", next);

	return 0;
}