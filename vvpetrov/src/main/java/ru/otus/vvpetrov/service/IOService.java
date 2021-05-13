package ru.otus.vvpetrov.service;

import java.util.List;

public interface IOService {
    void outputString(String msg);
    String inputString();
    List<Integer> readIntList(String str);
}
