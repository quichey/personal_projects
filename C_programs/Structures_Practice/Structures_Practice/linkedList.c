//
//  linkedList.c
//  Structures_Practice
//
//  Created by Kyle Ngo on 5/28/18.
//  Copyright © 2018 Kyle Ngo. All rights reserved.
//

#include <stdio.h>

struct test {
    int x;
};

struct linkedList {
    int value;
    struct linkedList * next;
} empty;

struct linkedList this;
int size = 0;

void add(int);
int get(int);

int main() {
    extern struct linkedList this;
    
    this = empty;
    
    add(1);
    
    add(2);
    
    printf("%d\n", get(0));
    
    printf("%d\n", get(1));
    
    return 0;
}

void add(int x) {
    extern struct linkedList this;
    struct linkedList that = this;
    this = (struct linkedList) {x, &that};
    size++;
}

int get(int i) {
    extern struct linkedList this;
    if (i >= size) {
        printf("Error -- Index Out of Bounds Exception:%d \n", i);
        return 0;
    }
    
    int n = 0;
    struct linkedList curr = this;
    while (n < i) {
        curr = * curr.next;
        n++;
    }
    return curr.value;
}


