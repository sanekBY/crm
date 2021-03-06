package by.shalukho.dto;


import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface ConnectedDto {
    @SuppressWarnings("rawtypes")
    Class<? extends AbstractDto> value();
}
