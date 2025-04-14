package com.emission.lcwd.user.service.services.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestClass {

    public static void main(String[] args) {

        String str = "Krishna is a Java programmer";

        char[] charArray = str.toCharArray();

        // print repeating string
        Map<Character, Long> collect = str.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new,Collectors.counting()));

        Optional<Map.Entry<Character, Long>> first = collect.entrySet().stream().filter(
                characterLongEntry -> characterLongEntry.getValue() > 1
        ).findFirst();

        System.out.println(first.get().getKey());

    }
}
