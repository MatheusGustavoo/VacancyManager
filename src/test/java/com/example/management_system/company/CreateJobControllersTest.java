package com.example.management_system.company;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.assertj.MockMvcTester.MockMvcRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import javax.swing.Spring;

import com.example.management_system.company.DTO.CreateJobDTO;
import com.example.management_system.company.entities.CompanyEntity;
import com.example.management_system.company.repositories.CompanyRepository;
import com.example.management_system.utils.TestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllersTest {
    private MockMvc mockMvc;


    @Autowired
    private WebApplicationContext context;


    @Autowired 
    private CompanyRepository companyRepository; ;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity()).build();

    }

    @Test
    public void create_job_success() throws Exception {

        var company = CompanyEntity.builder().cnpj("12345678901").name("TEST").email("TEST@dsnd.com").password("secret").build();

        companyRepository.saveAndFlush(company);

        var createdNewJob = CreateJobDTO.builder().benefit("TEST").descrpition("DESCRIPTION_TEST").level("LEVEL_TEST").build();

        var result = mockMvc.perform(post("/company/job").contentType(MediaType.APPLICATION_JSON).content(TestUtils.asJsonString(createdNewJob)).header("Authorization", TestUtils.generateToken(company.getCnpj(), "secret"))).andExpect(MockMvcResultMatchers.status().isOk());        
    }
    @Test
    public void create_job_without__valid_company() throws Exception{
        var createdNewJob = CreateJobDTO.builder().benefit("TEST").descrpition("DESCRIPTION_TEST").level("LEVEL_TEST").build();

        var result = mockMvc.perform(post("/company/job").contentType(MediaType.APPLICATION_JSON).content(TestUtils.asJsonString(createdNewJob)).header("Authorization", TestUtils.generateToken("00000", "secret"))).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


}
