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
    
    while ((input = getch()) != EOF) {
        putchar(input);
        if (input == 'e') {
            for (int i = 0; i < 5; i++) {
                ungetch(input);
                input = getch();
                putchar(input);
            }
        }
    }
    return 0;
}
