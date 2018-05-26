package com.example.restdocs;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author wuxii@foxmail.com
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationDocumentation {

    private MockMvc mockMvc;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)//
                .apply(documentationConfiguration(this.restDocumentation))//
                .build();
    }

    @Test
    public void echo() throws Exception {
        this.mockMvc
                .perform(get("/")//
                        .accept(MediaType.APPLICATION_JSON)//
                        .param("name", "wuxii"))//
                .andExpect(status().isOk())//
                .andDo(document("echo", //
                        requestParameters(//
                                parameterWithName("name").optional().description("用户名")), //
                        responseFields(//
                                fieldWithPath("Hello").description("Hello"))));
    }

    @Test
    public void test() throws Exception {
        this.mockMvc
                .perform(get("/user")//
                        .accept(MediaType.APPLICATION_JSON))//
                .andExpect(status().isOk())//
                .andDo(document("user", //
                        responseFields(//
                                fieldWithPath("username").description("用户名"), //
                                fieldWithPath("password").optional().description("密码"))));
    }

}
