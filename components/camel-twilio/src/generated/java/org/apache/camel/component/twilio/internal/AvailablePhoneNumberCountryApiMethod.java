/*
 * Camel ApiMethod Enumeration generated by camel-api-component-maven-plugin
 */
package org.apache.camel.component.twilio.internal;

import java.lang.reflect.Method;
import java.util.List;

import com.twilio.rest.api.v2010.account.AvailablePhoneNumberCountry;

import org.apache.camel.support.component.ApiMethod;
import org.apache.camel.support.component.ApiMethodArg;
import org.apache.camel.support.component.ApiMethodImpl;

import static org.apache.camel.support.component.ApiMethodArg.arg;

/**
 * Camel {@link ApiMethod} Enumeration for com.twilio.rest.api.v2010.account.AvailablePhoneNumberCountry
 */
public enum AvailablePhoneNumberCountryApiMethod implements ApiMethod {

    FETCHER(
        com.twilio.rest.api.v2010.account.AvailablePhoneNumberCountryFetcher.class,
        "fetcher",
        arg("pathCountryCode", String.class)),

    FETCHER_1(
        com.twilio.rest.api.v2010.account.AvailablePhoneNumberCountryFetcher.class,
        "fetcher",
        arg("pathAccountSid", String.class),
        arg("pathCountryCode", String.class)),

    READER(
        com.twilio.rest.api.v2010.account.AvailablePhoneNumberCountryReader.class,
        "reader"),

    READER_1(
        com.twilio.rest.api.v2010.account.AvailablePhoneNumberCountryReader.class,
        "reader",
        arg("pathAccountSid", String.class));

    private final ApiMethod apiMethod;

    private AvailablePhoneNumberCountryApiMethod(Class<?> resultType, String name, ApiMethodArg... args) {
        this.apiMethod = new ApiMethodImpl(AvailablePhoneNumberCountry.class, resultType, name, args);
    }

    @Override
    public String getName() { return apiMethod.getName(); }

    @Override
    public Class<?> getResultType() { return apiMethod.getResultType(); }

    @Override
    public List<String> getArgNames() { return apiMethod.getArgNames(); }

    @Override
    public List<Class<?>> getArgTypes() { return apiMethod.getArgTypes(); }

    @Override
    public Method getMethod() { return apiMethod.getMethod(); }
}
