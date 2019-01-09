//
//  getchTest.c
//  output
//
//  Created by Kyle Ngo on 5/20/18.
//  Copyright © 2018 Kyle Ngo. All rights reserved.
//

#include <stdio.h>
#include "../header.h"

int main() {
    int input;
    
    while ((input = getchar()) != EOF) {
        ungetch(input);
    }
    
    buffer[bufferIndex] = '\0';
    
    printNewLine();
    
    printf("%s", buffer);
    
    printNewLine();
    
    return 0;
}
