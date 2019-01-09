//
//  entab.c
//  output
//
//  Created by Kyle Ngo on 5/19/18.
//  Copyright © 2018 Kyle Ngo. All rights reserved.
//

#include <stdio.h>

#define MAXLENGTH 100
#define MAXLINE 10
#define TAB_AMOUNT 8
#define NOT_EOF 1

const int entabArraySize = MAXLENGTH / TAB_AMOUNT + MAXLENGTH % TAB_AMOUNT;
int lineNum = 0;
char returnedChar;
char entabArray[entabArraySize];

char outputLines[MAXLINE][MAXLENGTH];

int getLine(void);

void entab(int index);

void displayOutput(void);

int copy(int index);

int main() {
    while (getLine() != EOF);
    
    displayOutput();
    
    return 0;
}

int getLine() {
    if (lineNum == MAXLINE)
        return EOF;
    
    extern char outputLines[][MAXLENGTH];
    extern char returnedChar;
    
    int input = EOF;
    int i;
    
    for (i = 0; i < MAXLENGTH && (input = getchar()) != EOF && input != '\n'; i++) {
        if (input == ' ') {
            entab(i);
            i = copy(i);
            input = returnedChar;
        }
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

void entab(int index) {
    extern char entabArray[];
    extern char returnedChar;
    
    int input = EOF;
    
    int entabArrayIndex = 0;
    int blankCount = 1;
    
    for (int i = index + 1; i < MAXLENGTH && (input = getchar()) == ' '; i++, blankCount++) {
        if (i % TAB_AMOUNT == TAB_AMOUNT - 1) {
            entabArray[entabArrayIndex++] = '\t';
            blankCount = -1;
        }
    }
    
    
    returnedChar = input;
    
    if (returnedChar == '\t') {
        entabArray[entabArrayIndex] = '\0';
        return;
    }
    
    for (int i = 0; i < blankCount; i++, entabArrayIndex++) {
        entabArray[entabArrayIndex] = ' ';
    }
    entabArray[entabArrayIndex] = '\0';
}

int copy(int index) {
    int i = 0;
    while ((outputLines[lineNum][index + i] = entabArray[i]) != '\0')
        i++;
    return index + i;
}

void displayOutput() {
    extern char outputLines[][MAXLENGTH];
    
    putchar('\n');
    
    for (int i = 0; i < lineNum; i++)
        printf("%s", outputLines[i]);
    
    putchar('\n');
}
