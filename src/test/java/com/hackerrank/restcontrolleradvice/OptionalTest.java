package com.hackerrank.restcontrolleradvice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class OptionalTest {
    @Test
    void testMapElse1(){
        Optional<String> data = Optional.of("hello");
        String out = data.map(s->s+"-001").orElse("world");
        Assertions.assertEquals("hello-001", out);
    }

    @Test
    void testMapElse2(){
        Optional<String> data = Optional.ofNullable(null);
        String out = data.map(s->s.toUpperCase()).orElse("world");
        Assertions.assertEquals("world", out);
    }
}
