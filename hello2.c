#include <stdio.h>

struct Score {
    int kor;
    int eng;
    int math;
    int sum;
    float aver;
};

typedef struct Score Score;

void printScore(struct Score*);

int main() {
    int i;
    Score s;

    s.kor = 100;
    s.eng = 100;
    s.math = 100;

    printScore(&s);

    Score scores[3];

    scores[0].kor = 80;
    scores[0].eng = 70;
    scores[0].math = 60;

    printScore(&scores[0]);

    return 0;
}

void printScore(Score* p) {
    p->sum = p->kor + p->eng + p->math;
    p->aver = p->sum / 3;
    printf("%d, %d, %d, %d, %f\n", p->kor, p->eng, p->math, p->sum, p->aver);
}

