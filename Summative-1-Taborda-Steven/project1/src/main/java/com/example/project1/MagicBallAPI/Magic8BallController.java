package com.example.project1.MagicBallAPI;
import java.util.Random;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Magic8BallController {

    private final Random random = new Random();
    private final String[] answers = {
            "It is certain.",
            "It is decidedly so.",
            "Without a doubt.",
            "Yes, definitely.",
            "You may rely on it.",
            "As I see it, yes.",
    };

    @PostMapping("/magic")
    public Answer shake(@RequestBody(required = false) Question question) {
        int index = random.nextInt(answers.length);
        if (question == null) {
            Question placeholder = new Question(-1, "");
            return new Answer(placeholder.getId(), placeholder.getQuestion(), answers[index]);
        }
        return new Answer(question.getId(), question.getQuestion(), answers[index]);
    }
}
