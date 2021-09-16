package com.company;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class HelloServiceTest {
    private static final String welcome = "Hello";
    private static final String fallbackIdWelcome = "Hola";

    @Test
    public void testNullPrepareGreetingReturnsFallbackName() {

//        given
        var mockRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService(mockRepository);
//        when
        var result = SUT.prepareGreeting(null, "-1");

//        then
        Assert.assertEquals(welcome + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void testNamePrepareGreetingReturnsGreetingsWithName() {

//        given
        String name = "test";
        var SUT = new HelloService();
//        when
        var result = SUT.prepareGreeting(name, "-1");

//        then
        Assert.assertEquals(welcome + " " + name + "!", result);
    }

    @Test
    public void testLangNullPrepareGreetingReturnsGreetingsWithFallbackIdLang() {

//        given
        var mockRepository = fallbackLangIdRepository();
        var SUT = new HelloService(mockRepository);
//        when
        var result = SUT.prepareGreeting(null, null);

//        then
        Assert.assertEquals(fallbackIdWelcome + " " + HelloService.FALLBACK_NAME + "!", result);
    }
    @Test
    public void testLangTextPrepareGreetingReturnsGreetingsWithFallbackIdLang() {

//        given
        var mockRepository = fallbackLangIdRepository();
        var SUT = new HelloService(mockRepository);
//        when
        var result = SUT.prepareGreeting(null, "abc");

//        then
        Assert.assertEquals(fallbackIdWelcome + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    private LangRepository alwaysReturningHelloRepository() {
        return new LangRepository() {
            @Override
            Optional<Lang> findById(Long id) {
                return Optional.of(new Lang(null, welcome, null));
            }
        };
    }

    private LangRepository fallbackLangIdRepository() {
        return new LangRepository() {
            @Override
            Optional<Lang> findById(Long id) {
                if(id.equals(HelloService.FALLBACK_LANG.getId())) {
                    return Optional.of(new Lang(null, fallbackIdWelcome, null));
                }
                return Optional.empty();
            }
        };
    }
}

