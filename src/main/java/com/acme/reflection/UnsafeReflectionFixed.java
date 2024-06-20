package com.acme.reflection;

import io.github.pixee.security.Reflection;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/** {@link UnsafeReflection}, but with the expected hardening against unsafe reflection. */
@Path("/unsafe-reflection-fixed")
public class UnsafeReflectionFixed {

  @GET
  public String hello(@QueryParam("translator") final String translationStrategy) {
    final var translator = loadTranslatorByName(translationStrategy);
    return translator.translate("Hello, world!");
  }

  private static TranslatorStrategy loadTranslatorByName(final String translationStrategy) {
    final Class<?> translatorClazz;
    try {
      translatorClazz = Reflection.loadAndVerify("com.acme." + translationStrategy);
    } catch (ClassNotFoundException e) {
      throw new IllegalArgumentException("Invalid translator: " + translationStrategy, e);
    }
    if (TranslatorStrategy.class.isAssignableFrom(translatorClazz)) {
      throw new IllegalArgumentException("Invalid translator: " + translationStrategy);
    }
    final Constructor<?> translatorCtor;
    try {
      translatorCtor = translatorClazz.getConstructor();
    } catch (NoSuchMethodException e) {
      throw new IllegalStateException(
          "Translator " + translationStrategy + " is missing a no-args constructor", e);
    }
    final TranslatorStrategy translator;
    try {
      translator = (TranslatorStrategy) translatorCtor.newInstance();
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      throw new IllegalStateException("Failed to initialize translator " + translationStrategy, e);
    }
    return translator;
  }
}
