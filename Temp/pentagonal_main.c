/*!	\file		pentagonal_main.c
	\author		Garth Santor
	\date		2019-01-17

	Print a formatted list of pentagonal numbers.

	About:
	maxDigits = (unsigned)floor(log10((double)numbers[elements - 1]) + 1);
	 
	Recall:
	log10(10) = 1.0, where (10) is like (10^1)
	log10(100) = 2.0, where (100) is like (10^2)

	If numbers[elements-1] = 152, then:
	log10(152) = 2.?,
	(unsigned)floor(log10(152)) + 1 = 2 + 1 = 3 -> 152 has 3 digits
*/


#include <stdio.h>
#include <stdint.h>
#include <stdbool.h>
#include <math.h>


// Allocate an array to hold the values, zero initialized.
#define CAPACITY 1000u
uint64_t numbers[CAPACITY];


/*!	Compute the n-th pentagonal number. */
uint64_t pentagonal(uint64_t n)
{
	return (3 * n * n - n) / 2;
}

/*!	Process entry point. */
int main()
{
	// Get the number of elements to compute?
	//int32_t elements = 10;
	printf("Enter the number of elements to compute: ");
	int32_t elements;	
	//(void)scanf_s("%d", &elements);

	//Test for valid data type, then ensure within the range between 0 and CAPACITY 
	int read;
	while ((read = scanf_s("%d", &elements)) != 1 || elements > CAPACITY || elements <= 0)
	{
		if (read == 0)
		{
			printf("Error invalid data type\n");

			int ch;
			while ((ch = getchar()) != EOF && ch != '\n');
		}
		else if (elements <= 0)
			printf("Error, min number of elements must exceed zero\n");
		else if (elements > CAPACITY)
			printf("Error, %d exceeds the max capacity of %lu\n", elements, CAPACITY);	
	}

	// Compute the list of pentagonal numbers
	for (size_t i = 0; i < elements; ++i)
		numbers[i] = pentagonal(i);

	// Compute the number of digits in the longest result
	unsigned int maxDigits = 0;
	if (elements > 0)
		maxDigits = (unsigned)floor(log10((double)numbers[elements - 1]) + 1);

	// Print the list in aligned columns, padding the second column to align the longest digit
	for (size_t i = 0; i < elements; ++i)
	{
		printf("[%2zu] = %*llu\n", i+1,maxDigits, numbers[i]);
		//printf("[%2zu] = %10llu\n", i + 1,  numbers[i]);
	}
}