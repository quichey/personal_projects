//
//  getInput.c
//  output
//
//  Created by Kyle Ngo on 5/23/18.
//  Copyright © 2018 Kyle Ngo. All rights reserved.
//

#include <stdio.h>
#include "../header.h"

#define MAX_LINE_LENGTH 100
#define MAX_LINE_NUMBER 10

char lines[MAX_LINE_NUMBER][MAX_LINE_LENGTH];
int lineNum = 0;

void getInput() {
    void initialize(void);
    int getLine(void);
    
    initialize();
    
    while (getLine() != EOF);
}

int getLine() {
    if (lineNum == MAX_LINE_NUMBER) {
        return EOF;
    }
    int input;
    
    int i;
    for (i = 0; i < MAX_LINE_LENGTH && (input = getch()) != EOF && input != '\n'; i++) {
        lines[lineNum][i] = input;
    }
    
    if (input == '\n') {
        lines[lineNum][i++] = '\n';
        if (i < MAX_LINE_LENGTH)
            lines[lineNum][i] = '\0';
    } else if (input == EOF) {
        return EOF;
    }
    lineNum++;
    return !EOF;
}

void initialize() {
    for (int i = 0; i < MAX_LINE_NUMBER; i++) {
        lines[i][0] = '\0';
    }
}

void printAllInput() {
    for (int i = 0; i < MAX_LINE_NUMBER; i++) {
        printf("%s", lines[i]);
    }
}
