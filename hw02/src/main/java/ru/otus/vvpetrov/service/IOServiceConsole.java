package ru.otus.vvpetrov.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.vvpetrov.exception.ExceptionIOService;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IOServiceConsole implements IOService {
    private final PrintStream systemOut;
    private final BufferedReader reader;

    public IOServiceConsole(@Value("#{T(java.lang.System).out}") PrintStream systemOut,
                            @Value("#{T(java.lang.System).in}") InputStream systemIn) {
        this.systemOut = systemOut;
        this.reader = new BufferedReader(new InputStreamReader(systemIn));
    }

    @Override
    public void outputString(String msg) {
        systemOut.println(msg);
    }

    @Override
    public String inputString() {
        String answerString = "";
        try {
            answerString = reader.readLine();
        } catch (Exception e) {
            throw new ExceptionIOService(" Error during read string form console ", e);
        }
        return answerString;
    }

    @Override
    public List<Integer> readIntList() {
        List<Integer> answerList = new LinkedList<>();
        try {
            answerList = Arrays.stream(reader.readLine().trim().split(","))
                    .mapToInt(Integer::parseInt).boxed()
                    .collect(Collectors.toList());
        } catch (Exception e) {
            outputString("Error during read string form console" + e.getMessage());
        }
        return answerList;
    }
}
