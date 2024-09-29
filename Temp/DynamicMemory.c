/*
Janice Manning
February, 2021
Introduction to dynamic arrays
*/

#define _CRTDBG_MAP_ALLOC
//include the C Runtime and Debug Library to use a memory leak detection function
#include <crtdbg.h>

#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>

int main()
{
	size_t capacity = 4;
	size_t size = 0;

	//malloc allocates type # of bytes of unitialized storage
	//4 * 4 bytes = 16B
	int* arr = (int*)malloc(capacity * sizeof(int));
	//if allocation unsuccessful, quit the program, not enough memory
	if (arr == NULL)	
	{
		printf("Error: memory allocation failed");		
		return EXIT_FAILURE;
	}
		
	//if allocation successful, arr points first address
	//arr must be deallocated with free() or realloc()
	printf("%zu bytes allocated.  Now for some ints:\n", capacity * sizeof * arr);
	for (size_t i = 0; i < capacity; ++i)
	{
		printf("%d ", arr[i] = i);
		++size;
	}
	printf("\n");
	printf("Capacity = %zu\n", capacity);
	printf("Size = %zu\n", size);

	//Simulate an expansion operation
	if (size == capacity)
	{
		//reallocate array, to have more capacity
		int* bigArr = (int*)realloc(arr, (capacity *= 2) * sizeof(int));

		if (bigArr == NULL)
		{
			printf("Error: memory allocation failed");

			free(arr);
			return EXIT_FAILURE;
		}

		arr = bigArr;
	}
		
	for (size_t i = size; i < capacity; ++i)
	{
		printf("%d ", arr[i] = i);
		++size;
	}
	printf("\n");
	printf("Capacity = %zu\n", capacity);
	printf("Size = %zu\n", size);

	//free(arr);

	//return EXIT_SUCCESS;
	
	//will report memory leaks in output using the DEBUGGER!!!
	_CrtDumpMemoryLeaks();
}