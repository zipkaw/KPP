package com.example.triangle.services;

import org.springframework.stereotype.Component;

@Component
public class Counter {
    private int count;
    Counter(){
        count = 0;
    }

    public synchronized int getCount(){
        return count;
    }

    public synchronized void addCount(){
        this.count = getCount() + 1;
    }
}