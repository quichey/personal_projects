//
//  header.h
//  output
//
//  Created by Kyle Ngo on 5/20/18.
//  Copyright © 2018 Kyle Ngo. All rights reserved.
//

#ifndef header_h
#define header_h


#endif /* header_h */

#include <stdio.h>
#include <ctype.h>

#define BUFFERSIZE 100

char buffer[BUFFERSIZE];
int bufferIndex = 0;

int getch() {
    return (bufferIndex > 0) ? buffer[--bufferIndex] : getchar();
}

void ungetch(int c) {
    if (bufferIndex == BUFFERSIZE)
        printf("Too many chars in buffer");
    buffer[bufferIndex++] = c;
}

void printNewLine() {
    putchar('\n');
}

void printString(char * string) {
    printf("%s", string);
}

void skipBlanks() {
    int input;
    
    while ((input = getch()) == ' ')
        ;
    
    ungetch(input);
}

void printInput() {
    int input;
    
    while ((input = getch()) != EOF) {
        putchar(input);
    }
}

float e(int i) {
    float start = 1.0;
    
    if (i > 0) {
        for (int j = 0; j < i; j++) {
            start = start * 10;
        }
    } else if (i < 0) {
        for (int j = 0; j > i; j--) {
            start = start / 10;
        }
    }
    
    return start;
}
