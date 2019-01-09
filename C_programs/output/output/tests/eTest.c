//
//  eTest.c
//  output
//
//  Created by Kyle Ngo on 5/21/18.
//  Copyright © 2018 Kyle Ngo. All rights reserved.
//

#include <stdio.h>
#include "../header.h"

int main() {
    float x = e(-5);
    
    printf("%f\n", x);
    
    x = e(10);
    
    printf("%f\n", x);
    
    x = e(0);
    
    printf("%f\n", x);
    
    return 0;
}
