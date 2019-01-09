//
//  compileBraces.c
//  output
//
//  Created by Kyle Ngo on 5/24/18.
//  Copyright © 2018 Kyle Ngo. All rights reserved.
//

#include <stdio.h>
#include "../actualHeader.h"

#define FAIL -1
#define NOT_FAIL -3
#define PREMATURE_CLOSE -2

int charNum = 0;
int currentLineNum = 0;

void incrementPointer(void);
void nextLine(void);
int validateBraces(void);
int compileBraces(void);

void runCompileBraces() {
    int outcome = compileBraces();
    printf("\n");
    if (outcome == FAIL)
        printf("Failed to compile: Brace '{' not closed\n");
    else if (outcome == PREMATURE_CLOSE)
        printf("Failed to compile: Premature closeure '}'\n");
    else
        printf("Compilation successful\n");
}

int compileBraces() {
    getInput();
    
    int input;
    
    while (currentLineNum < MAX_LINE_NUMBER) {
        input = lines[currentLineNum][charNum];
        switch (input) {
            case '}':
                return PREMATURE_CLOSE;
            case '\0':
                nextLine();
                break;
            case '{':
                incrementPointer();
                if (validateBraces() == FAIL)
                    return FAIL;
                break;
            default:
                incrementPointer();
                break;
        }
    }
    return NOT_FAIL;
}

int validateBraces() {
    while (currentLineNum < MAX_LINE_NUMBER) {
        char current = lines[currentLineNum][charNum];
        switch (current) {
            case '}':
                incrementPointer();
                return NOT_FAIL;
            case '{':
                incrementPointer();
                if (validateBraces() == FAIL)
                    return FAIL;
                break;
            case '\0':
                nextLine();
                break;
            default:
                incrementPointer();
                break;
        }
    }
    return FAIL;
}

void incrementPointer() {
    charNum = (charNum + 1) % MAX_LINE_LENGTH;
    if (charNum == 0)
        currentLineNum++;
}

void nextLine() {
    currentLineNum++;
    charNum = 0;
}
