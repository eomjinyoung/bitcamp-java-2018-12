#include <stdio.h>

// 호출하려는 함수의 정보(function prototype = method signature)를 알려준다.
void m1(int*);
int plus(int, int);
int minus(int, int);
int eom_multiple(int, int);
int eom_plus(int, int);

int main() {
    int i = 100;
    printf("main(): i = %d\n", i);
    m1(&i); // call by reference
    printf("main(): i = %d\n", i);

    printf("100 + 200 = %d\n", plus(100, 200));
    printf("100 + 200 = %d\n", eom_plus(100, 200));
    printf("100 - 200 = %d\n", minus(100, 200));
    printf("100 - 200 = %d\n", eom_multiple(100, 200));
}

void m1(int* p) {
    *p = 200;
}

