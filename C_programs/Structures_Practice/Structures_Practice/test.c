//
//  test.c
//  Structures_Practice
//
//  Created by Kyle Ngo on 6/21/18.
//  Copyright © 2018 Kyle Ngo. All rights reserved.
//

#include <stdio.h>
#include <stdint.h>
#include <inttypes.h>
#define YES 1

int multhi(uint32_t a, uint32_t b) {
    uint32_t    a_lo = (uint16_t)a;
    uint32_t    a_hi = a >> 16;
    uint32_t    b_lo = (uint16_t)b;
    uint32_t    b_hi = b >> 16;

    uint32_t    a_x_b_hi =  a_hi * b_hi;
    uint32_t    a_x_b_mid = a_hi * b_lo;
    uint32_t    b_x_a_mid = b_hi * a_lo;
    uint32_t    a_x_b_lo =  a_lo * b_lo;

    uint32_t    carry_bit = ((uint32_t)(uint16_t)a_x_b_mid +
                             (uint32_t)(uint16_t)b_x_a_mid +
                             (a_x_b_lo >> 16) ) >> 16;

    uint32_t    multhi = a_x_b_hi +
    (a_x_b_mid >> 16) + (b_x_a_mid >> 16) +
    carry_bit;

    return multhi;
}

int main() {
    unsigned short int z = 65535;
    printf("vii. %d\n", z);
    printf("vii. %d\n", (signed short int) z);
    uint32_t x = 0xefffe800;
    uint32_t w = 0xefffe800;
    int64_t y = (int64_t) x;
    int32_t u = x * w;
    printf("%lld\n", y);
    printf("%x\n", u);
    int32_t v = (int64_t) x * (int64_t) w >> 31 >> 1;
    printf("%x\n", v);
    x = 0xffffffff;
    w = 0xffffffff;
    v = (uint32_t) ((uint64_t) x * (uint64_t) w >> 31 >> 1);
    uint64_t q = (uint64_t) x * (uint64_t) w;
    printf("%lld\n",q);
    q = (uint32_t) x * (uint32_t) w;
    printf("%lld\n",q);
    printf("%x\n", v);
    int a = -1;
    int b = -1;
    printf("multhi: %x\n", multhi(a, b));
    int a[] = {1, 2, 3};
    int temp[1];
    temp = &(a[1]);
    return 0;
}
