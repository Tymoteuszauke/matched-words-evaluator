package org.evaluator.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.evaluator.exceptions.FileNotFoundException;

public enum PropertiesLoader {
  INSTANCE;

  private final Properties properties;

  PropertiesLoader() {
    this.properties = loadConfigProperties();
  }

  public String getProperty(String propertyName) {
    return properties.getProperty(propertyName);
  }

  private Properties loadConfigProperties() {
    try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
      Properties prop = new Properties();
      prop.load(input);

      return prop;
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    throw new FileNotFoundException("Could not find application configuration file!");
  }
}
