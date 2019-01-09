#include <stdio.h>

// 호출하려는 함수의 정보(function prototype = method signature)를 알려준다.
void m1(int*);

int main() {
    int i = 100;
    printf("main(): i = %d\n", i);
    m1(&i); // call by reference
    printf("main(): i = %d\n", i);
}

void m1(int* p) {
    *p = 200;
}

