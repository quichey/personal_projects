//
//  getFloat.c
//  output
//
//  Created by Kyle Ngo on 5/20/18.
//  Copyright © 2018 Kyle Ngo. All rights reserved.
//

#include <stdio.h>
#include "../getInt/getInt.c"

#define FLOAT_DEC_NUM 6
#define IS_FLOAT 1
#define NOT_FLOAT -1
#define CONTINUE 1

int primary;
int decimals[FLOAT_DEC_NUM + 1];

int setPrimary(void);
int setDecimals(void);
int getFloat(float *);
void setDecimalPart(float *);

/*
 Returns -1 if the next input is not a floating point
 */
int main() {
    float c;
    getFloat(&c);
    printf("%f\n", c);
    return 0;
}

int getFloat(float *p) {
    extern int primary;
    extern int decimals[];
    
    extern int sign;
    int thisSign;
    
    skipBlanks();
    
    int primaryPart;
    getInt(&primaryPart);
    thisSign = sign;
    
    int input;
    
    if ((input = getch()) != '.') {
        return NOT_FLOAT;
    }
    
    float decimalPart;
    setDecimalPart(&decimalPart);
    
    if (thisSign == POS) {
        *p = primaryPart + decimalPart;
    } else {
        *p = primaryPart - decimalPart;
    }
    
    if (tolower(input = getch()) == 'e') {
        int exp;
        getInt(&exp);
        *p = *p * e(exp);
    } else {
        ungetch(input);
    }
    
    return IS_FLOAT;
}

int setPrimary() {
    extern int primary;
    int input = getch();
    if (!isdigit(input)) {
        ungetch(input);
        return NOT_FLOAT;
    } else {
        primary = input - '0';
        return CONTINUE;
    }
}

int setDecimals() {
    extern int decimals[];
    int input;
    
    if ((input = getch()) != '.') {
        ungetch(primary);
        ungetch(input);
        return NOT_FLOAT;
    }
    
    int i;
    
    for (i = 0; i < FLOAT_DEC_NUM && isdigit(input = getch()) ; i++)
        decimals[i] = input - '0';
    
    ungetch(input);
    
    if (i <= FLOAT_DEC_NUM) {
        decimals[i] = '\0';
        return CONTINUE;
    } else
        return NOT_FLOAT;
}

int numberOfZerosSkip() {
    int input;
    int count = 0;
    while ((input = getch()) == '0') {
        count++;
    }
    ungetch(input);
    return count;
}

void setDecimalPart(float * p) {
    int numOfZeros = numberOfZerosSkip();
    int soFar;
    
    int exp = getInt(&soFar);
    if (exp == NOT_INT) {
        *p = 0;
        return;
    }
    int negExp = -1 * exp;
    *p = soFar * e(negExp);
    *p = (*p) * e(-numOfZeros);
}
