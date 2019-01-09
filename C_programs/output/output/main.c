//
//  main.c
//  output
//
//  Created by Kyle Ngo on 5/19/18.
//  Copyright © 2018 Kyle Ngo. All rights reserved.
//

#include <stdio.h>

int main(int argc, const char * argv[]) {
    float c = 0.123456789;
    c = c - 0.1234567;
    c = c * 100000;
    c = 1.2e30;
    printf("%f\n", c);
    return 0;
}
