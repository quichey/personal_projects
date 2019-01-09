//
//  passByValue.c
//  Structures_Practice
//
//  Created by Kyle Ngo on 5/25/18.
//  Copyright © 2018 Kyle Ngo. All rights reserved.
//

#include <stdio.h>

#define THAT 5.0
#define THIS THAT + 5
#define min(a, b)   ((a) < (b) ? (a) : (b))
#define max (a, b)  (( a) > (b) ? (a) : (b) )

int x = THIS;
int y = 0;

struct point {
    int x;
    int y;
} one = {  4, 5};

void doThis(struct point * p) {
    (*p).x = 10;
    (*p).y = 12;
}

int main() {
    struct point this;
    this.x = 5;
    this.y = 6;
    
    doThis(&this);
    
    printf("%d\n", this.x);
    
    printf("%f\n", THIS);
    
    printf("%f\n", THAT);
    
    printf("%f\n", min(3.0, 4));
    
    int z;
    
    printf("%p\n", &z);
    
    printf("%d\n", one.x);
    
    return 0;
}
