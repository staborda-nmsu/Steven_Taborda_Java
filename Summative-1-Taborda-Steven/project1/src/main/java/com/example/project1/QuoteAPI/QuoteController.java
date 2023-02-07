package com.example.project1.QuoteAPI;

import java.util.Random;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {
    private static final Quote[] quotes = {
            new Quote(1, "Albert Einstein", "Imagination is more important than knowledge."),
            new Quote(2, "Steve Jobs", "Design is not just what it looks like and feels like. Design is how it works."),
            new Quote(3, "Nelson Mandela", "Education is the most powerful weapon which you can use to change the world."),
            new Quote(4, " Mahatma Gandhi", "Be the change that you wish to see in the world."),
            new Quote(5, "Martin Luther King Jr.", "Darkness cannot drive out darkness; only light can do that. Hate cannot drive out hate; only love can do that."),
            new Quote(6, "Confucius", "The man who asks a question is a fool for a minute, the man who does not is a fool for life."),
            new Quote(7, "Leo Tolstoy", "Everyone thinks of changing the world, but no one thinks of changing himself."),
            new Quote(8, "Mark Twain", "The secret of getting ahead is getting started."),
            new Quote(9, "Frank Zappa", "Without deviation from the norm, progress is not possible."),
            new Quote(10, "Ralph Waldo Emerson", "The only person you are destined to become is the person you decide to be.")
    };

    @GetMapping("/quote")
    public Quote getQuoteOfTheDay() {
        int randomIndex = new Random().nextInt(quotes.length);
        return quotes[randomIndex];
    }
}