//
//  fib.c
//  Structures_Practice
//
//  Created by Kyle Ngo on 5/29/18.
//  Copyright © 2018 Kyle Ngo. All rights reserved.
//

#include <stdio.h>

int fib(int n) {
    if (n <= 1)
        return 1;
    return fib(n-1) + fib(n-2);
}

int main(int argc, const char * argv[]) {
    if (argc >= 2) {
        const char * word = argv[1];
        int n = word[0] - '0';
        printf("%d\n", fib(n));
        int array[n];
        array[10] = 5;
        printf("%d\n", array[10]);
        printf("%d\n", n);
    }
    return 0;
}

int test(int n[]) {
    n[0] = 5;
    return 1;
}

struct options{
        unsigned int one:1, two:1, three:1, four:1, five:1, six:1, seven:1, eight:1, nine:1;
    };
