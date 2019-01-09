//
//  detab.c
//  output
//
//  Created by Kyle Ngo on 5/19/18.
//  Copyright © 2018 Kyle Ngo. All rights reserved.
//

#include "detab.h"
#include <stdio.h>

#define MAXLENGTH 100
#define MAXLINES 10
#define NOT_EOF 1
#define HADTAB 2
#define NOT_HADTAB 3

char outputLines[MAXLINES][MAXLENGTH];
int lineNum = 0;

int getLine(void);

int main() {
    extern char outputLines[][MAXLENGTH];
    
    putchar('\n');
    
    while (getLine() != EOF);
    
    putchar('\n');
    
    for (int i = 0; i < lineNum; i++)
        printf("%s", outputLines[i]);
    
    putchar('\n');
    
    return 0;
}

int getLine() {
    if (lineNum == MAXLINES)
        return EOF;
    
    extern char outputLines[][MAXLENGTH];
    int input = EOF;
    int state = HADTAB;
    
    int i;
    
    for (i = 0; i < MAXLENGTH && (input = getchar()) != EOF && input != '\n'; i++) {
        if (input == '\t') {
            while (i % 8 != 0 || state == HADTAB) {
                outputLines[lineNum][i] = ' ';
                i++;
                state = NOT_HADTAB;
            }
            i--;
            state = HADTAB;
        } else
            outputLines[lineNum][i] = input;
    }
    
    if (input == '\n') {
        outputLines[lineNum][i] = '\n';
        i++;
        if (i < MAXLENGTH)
            outputLines[lineNum][i] = '\0';
    }
    
    lineNum++;
    
    if (input == EOF)
        return EOF;
    else
        return NOT_EOF;
}
