package com.co.nisum.user.config;

import com.co.nisum.user.util.Parameter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.SimpleDateFormat;

@RunWith(MockitoJUnitRunner.class)
public class UserConfigTest {

    @InjectMocks
    private UserConfig userConfig;
    @Mock
    private Parameter parameter;


    @Test
    public void getSimpleDateFormatTest(){
        parameter.patternDateFormat = "MM-dd-yyyy";
        SimpleDateFormat result = userConfig.simpleDateFormat();
        Assert.assertTrue(result.toPattern().equals("MM-dd-yyyy"));
    }
}
