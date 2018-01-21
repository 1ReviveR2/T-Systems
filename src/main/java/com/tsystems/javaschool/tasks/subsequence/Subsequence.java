package com.tsystems.javaschool.tasks.subsequence;

import java.util.List;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")
    public boolean find(List x, List y) {
        boolean b;
        int k = 0, count = 0;
        if(x == null||y == null)
        {
            throw new IllegalArgumentException();
        }
        //двигаемся по первому списку, который будем искать во втором списке
        for(int i=0; i<x.size(); i++){
            //двигаемся по второму списку, в котором будем искать первый список
            for(int j=0+k; j<y.size(); j++){
                //проверяем не совпали ли элементы
                if(x.get(i)==y.get(j)){
                    //запоминаем на какой позиции нашли совпадение во втором массиве, чтобы на следующей итерации начать со следующей позиции
                    k=j+1;
                    //счетчик
                    count++;
                    //выходим из внутреннего цикла, нужный элемент мы уже нашли
                    break;
                }
            }
        }
        //проверяем совпал ли наш счетчик с размерностью списка
        return (count==x.size());
    }
}
