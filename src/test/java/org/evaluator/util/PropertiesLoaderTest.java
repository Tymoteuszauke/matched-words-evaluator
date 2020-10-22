package org.evaluator.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class PropertiesLoaderTest {

  @Test
  void shouldReturnTestProperty() throws IOException {
    assertEquals(" ,.", PropertiesLoader.INSTANCE.getProperty("input.delimiters"));
  }
}
