package com.acme.reflection;

/** Example of a configurable strategy. */
public interface TranslatorStrategy {
  /** Translates the given text. */
  String translate(String text);
}
