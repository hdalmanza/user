package com.co.nisum.user.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.SimpleDateFormat;

@RunWith(MockitoJUnitRunner.class)
public class GeneralUtilTest {
    @InjectMocks
    private GeneralUtil generalUtil;
    @Mock
    private  SimpleDateFormat simpleDateFormat;
    @Mock
    private   Parameter parameter;

    @Before
    public void setup(){
        parameter.labelMessage = "mensaje";
        parameter.secretKey = "myKey";
    }
    @Test
    public void buildMessageSuccessTest(){
        String responseExpect =  generalUtil.buildMessage(" El correo ya registrado");
        Assert.assertNotNull(responseExpect);
        Assert.assertEquals(responseExpect, "{\"mensaje\":\" El correo ya registrado\"}");
    }

    @Test
    public void getJwtTokenSuccessTest(){
        String responseExpect =  generalUtil.getJwtToken("Juan Perez");
        Assert.assertNotNull(responseExpect);
        Assert.assertTrue(responseExpect.contains("wiaWF0"));
    }


    @Test(expected = NullPointerException.class)
    public void getDateSuccessTest(){
        generalUtil.getDate();
    }

}
