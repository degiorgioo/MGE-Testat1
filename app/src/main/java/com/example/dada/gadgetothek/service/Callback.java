package com.example.dada.gadgetothek.service;

public interface Callback<T> {
    void onCompletion(T input);
    void onError(String message);
}
