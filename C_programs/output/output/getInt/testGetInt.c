//
//  testGetInt.c
//  output
//
//  Created by Kyle Ngo on 5/21/18.
//  Copyright © 2018 Kyle Ngo. All rights reserved.
//

#include <stdio.h>
#include "getInt.c"

int main() {
    int c;
    int digits = getInt(&c);
    printf("\n%d\n", c);
    printf("%d\n", digits);
    return 0;
}
