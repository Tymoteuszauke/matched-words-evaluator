package org.evaluator.core;

import java.util.List;

public class InputDataTestFixtures {

  public static final String FRUIT_SOURCE = "fruitSource";
  public static final String ANIMAL_SOURCE = "animalSource";
  public static final String MIXED_SOURCE = "mixedSource";

  public static List<String> someFruitData() {
    return List.of("apple", "orange", "pear", "grape", "pineapple");
  }

  public static List<String> someAnimalData() {
    return List.of("tiger", "elephant", "cat", "dog");
  }

  public static List<String> someMixedData() {
    return List.of("elephant", "grape", "cat", "pear");
  }
}
