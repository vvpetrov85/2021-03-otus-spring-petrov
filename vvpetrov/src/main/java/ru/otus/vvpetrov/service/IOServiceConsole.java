package ru.otus.vvpetrov.service;

import org.springframework.stereotype.Service;
import ru.otus.vvpetrov.exception.ExceptionIOService;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IOServiceConsole implements IOService {
    @Override
    public void outputString(String msg) {
        System.out.println(msg);
    }

    @Override
    public String inputString() {
        String answerString = "";
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        try {
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            answerString = bufferedReader.readLine();
        } catch (Exception e) {
            throw new ExceptionIOService(" Error: " + e.getMessage());
        }
        return answerString;
    }

    @Override
    public List<Integer> readIntList(String str) {
        List<Integer> answerList = new LinkedList<>();
        try {
            answerList = Arrays.stream(str.trim().split(","))
                    .mapToInt(Integer::parseInt).boxed()
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Your answer is wrong format. Error :" + e.getMessage());
            System.out.println("You must enter the correct answers as numeric values(for example 1,3)");
        }
        return answerList;
    }

}
