package com.example.project1.WordAPI;

import java.util.Random;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WordController {

    private final Random random = new Random();
    private final Word[] definitions = {
            new Word(1, "programming", "The process of designing, writing, testing, debugging, and maintaining the source code of computer software."),
            new Word(2, "algorithm", "A set of well-defined instructions in a computer program for solving a specific problem."),
            new Word(3, "debugging", "The process of finding and fixing errors in computer software."),
            new Word(4, "compiler", "A program that converts source code into machine code."),
            new Word(5, "source code", "The human-readable version of a computer program written in a programming language."),
            new Word(6, "binary", "A representation of data using only two symbols, typically 0 and 1."),
            new Word(7, "API", "An interface that allows software programs to communicate with each other."),
            new Word(8, "database", "A collection of data that is organized and stored so that it can be easily accessed, managed, and updated."),
            new Word(9, "server", "A computer program or a device that provides functionality for other programs or devices, called clients."),
            new Word(10, "client", "A computer program or a device that requests services or resources from a server."),
    };

    @GetMapping("/word")
    public Word getWordOfTheDay() {
        int index = random.nextInt(definitions.length);
        return definitions[index];
    }
}
