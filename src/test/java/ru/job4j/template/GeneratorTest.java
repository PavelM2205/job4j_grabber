package ru.job4j.template;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class GeneratorTest {

    @Test
    public void whenTemplateAndArgumentsAreCorrectly() {
        Generator generator = new FirstGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = Map.of("name", "Petr", "subject", "you");
        String expected = "I am a Petr, Who are you?";
        assertEquals(expected, generator.produce(template, args));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenArgumentsContainsKeyThatIsNotInTemplateThenException() {
        Generator generator = new FirstGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = Map.of("value", "Petr", "subject", "you");
        generator.produce(template, args);
    }

    @Test
    public void whenArgumentsContainsLessKeysThanTemplateThenException() {
        Generator generator = new FirstGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = Map.of("name", "Petr");
        generator.produce(template, args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenArgumentsContainsExtraKeyThenException() {
        Generator generator = new FirstGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = Map.of("name", "Petr", "subject", "you",
                "surname", "Ivanov");
        generator.produce(template, args);
    }
}