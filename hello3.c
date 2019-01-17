#include <stdio.h>

int plus(int a, int b) {
    return a + b;
}

int plus2(int a, int b, int c) {
    return a + b + c;
}

float plusF(float a, float b) {
    return a + b;
}

int main() {
    printf("result = %d\n", plus(100, 200));
    printf("result = %d\n", plus2(100, 200, 300));
    printf("result = %f\n", plusF(3.15f, 12.4f));
    return 0;
}


