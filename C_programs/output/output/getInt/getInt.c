//
//  getInt.c
//  output
//
//  Created by Kyle Ngo on 5/21/18.
//  Copyright © 2018 Kyle Ngo. All rights reserved.
//

#include <stdio.h>
#include "../header.h"

#define POS 1
#define NEG -1
#define NOT_INT -2

int getSign(void);
int getUnSigned(int * p);
int getInt(int * p);

int sign;

int getInt(int * p) {
    skipBlanks();
    
    extern int sign;
    sign = getSign();
    
    if (sign == NOT_INT)
        return NOT_INT;
    
    int numDigits = getUnSigned(p);
    
    if (numDigits == NOT_INT)
        return NOT_INT;
    
    *p = (*p) * sign;
    return numDigits ;
}

int getSign() {
    int input = getch();
    if (isdigit(input)) {
        ungetch(input);
        return POS;
    } else if (input == '+') {
        return POS;
    } else if (input == '-') {
        return NEG;
    } else {
        ungetch(input);
        return NOT_INT;
    }
}
           
int getUnSigned(int * p) {
    int input = getch();
    ungetch(input);
    if (!isdigit(input)) {
        return NOT_INT;
    }
    *p = 0;
    int numDigits = 0;
    while (isdigit(input = getch())) {
        *p = (*p) * 10 + (input - '0');
        numDigits++;
    }
    ungetch(input);
    return numDigits;
}
