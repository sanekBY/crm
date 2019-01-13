package by.shalukho.function;

import java.io.Serializable;
import java.util.function.Function;

@FunctionalInterface
public interface SerializableFunction<I, O> extends Function<I, O>, Serializable {
}
