//package com.sberbank.demoProject.authmicroservice;
//import com.github.tomakehurst.wiremock.WireMockServer;
//import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
//import com.sberbank.demoProject.authmicroservice.models.User;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.springframework.test.web.servlet.MockMvc;
//import org.testcontainers.containers.PostgreSQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//import org.testcontainers.shaded.com.fasterxml.jackson.core.type.TypeReference;
//import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
//import java.io.IOException;
//import java.net.URL;
//import java.util.Map;
//import static com.github.tomakehurst.wiremock.client.WireMock.*;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@Testcontainers
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//class AuthMicroserviceApplicationTests {
//
//    private static WireMockServer wireMockServerAdmin;
//    private static WireMockServer wireMockServerGoods;
//    private static Map<String, User> usersByRole;
//    private MockMvc mockMvc;
//    private static String PATH_USERS_JSON = "file:src/test/resources/users.json";
//
//    @Autowired
//    public AuthMicroserviceApplicationTests(MockMvc mockMvc) {
//        this.mockMvc = mockMvc;
//    }
//
//    static {
//        try {
//            usersByRole = new ObjectMapper().readValue(new URL(PATH_USERS_JSON),
//                    new TypeReference<Map<String, User>>() {});
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @BeforeAll
//    static void start(){
//        wireMockServerAdmin = new WireMockServer(WireMockConfiguration.options().port(8070));
//        wireMockServerGoods = new WireMockServer(WireMockConfiguration.options().port(8090));
//        wireMockServerAdmin.start();
//        wireMockServerGoods.start();
//    }
//
//    @AfterAll
//    static void stop(){
//        wireMockServerAdmin.stop();
//        wireMockServerGoods.stop();
//    }
//
//    @Container
//    public static PostgreSQLContainer container = new PostgreSQLContainer()
//            .withUsername("postgres")
//            .withPassword("pass")
//            .withDatabaseName("security");
//
//     @DynamicPropertySource
//     static void properties(DynamicPropertyRegistry source){
//         source.add("spring.datasource.url", container::getJdbcUrl);
//         source.add("spring.datasource.password", container::getPassword);
//         source.add("spring.datasource.username", container::getUsername);
//
//         source.add("zuul.routes.admin.url", ()-> "http://localhost:8070/");
//         source.add("zuul.routes.courses.url", ()-> "http://localhost:8090/");
//     }
//
//    /*
//    Проверка проксирования админа на микросервис для админа
//    * */
//    @Test
//    public void adminToMicroserviceServiceAdmin() throws Exception {
//        wireMockServerAdmin.stubFor(get("/api/v1")
//                        .withHeader("USER_ID", matching(usersByRole.get("ROLE_ADMIN").getId().toString()))
//                        .withHeader("ROLE_NAME", matching(usersByRole.get("ROLE_ADMIN").getRole().name()))
//                .willReturn(aResponse()
//                        .withStatus(200)));
//
//       this.mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/admin/api/v1")
//                       .with(httpBasic(usersByRole.get("ADMIN").getEmail(), usersByRole.get("ADMIN").getPassword())))
//               .andExpect(status().isOk());
//    }
//
//    /*
//    Проверка проксирования админа на микросервис для всех юзеров
//    * */
//    @Test
//    public void adminToMicroserviceServiceGoods() throws Exception {
//        wireMockServerGoods.stubFor(get("/api/v1")
//                    .withHeader("USER_ID", matching(usersByRole.get("ADMIN").getId().toString()))
//                    .withHeader("ROLE_NAME", matching(usersByRole.get("ADMIN").getRole().name()))
//                .willReturn(aResponse()
//                        .withStatus(200)));
//
//        this.mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/courses/api/v1")
//                        .with(httpBasic(usersByRole.get("ADMIN").getEmail(), usersByRole.get("ADMIN").getPassword())))
//                .andExpect(status().isOk());
//    }
//
//    /*
//    Проверка проксирования юзера на микросервис для админа
//    * */
//    @Test
//    public void userToMicroserviceServiceAdmin() throws Exception {
//        wireMockServerAdmin.stubFor(get("/api/v1")
//                    .withHeader("USER_ID", matching(usersByRole.get("USER").getId().toString()))
//                    .withHeader("ROLE_NAME", matching(usersByRole.get("USER").getRole().name()))
//                .willReturn(aResponse()
//                        .withStatus(200)));
//
//        this.mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/admin/api/v1")
//                        .with(httpBasic(usersByRole.get("USER").getEmail(), usersByRole.get("USER").getPassword())))
//                .andExpect(status().isForbidden());
//    }
//
//    /*
//    Проверка проксирования юзера на микросервис для всех юзеров
//    * */
//    @Test
//    public void userToMicroserviceServiceGoods() throws Exception {
//        wireMockServerGoods.stubFor(get("/api/v1")
//                    .withHeader("USER_ID", matching(usersByRole.get("USER").getId().toString()))
//                    .withHeader("ROLE_NAME", matching(usersByRole.get("USER").getRole().name()))
//                .willReturn(aResponse()
//                        .withStatus(200)));
//
//        this.mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/courses/api/v1")
//                        .with(httpBasic(usersByRole.get("USER").getEmail(), usersByRole.get("USER").getPassword())))
//                .andExpect(status().isOk());
//    }
//
//    /*
//    Проверка доступа анонима к ресурсам
//    * */
//
//    @Test
//    public void anonymousToMicroserviceServiceAdmin() throws Exception {
//        wireMockServerAdmin.stubFor(get("/api/v1")
//                .willReturn(aResponse()
//                        .withStatus(200)));
//
//        this.mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/admin/api/v1"))
//                .andExpect(status().isUnauthorized());
//    }
//
//    /*
//    Проверка ввода неправильного пароля
//    * */
//
//    @Test
//    public void wrongUserToMicroserviceServiceAdmin() throws Exception {
//        wireMockServerAdmin.stubFor(get("/api/v1")
//                .willReturn(aResponse()
//                        .withStatus(200)));
//
//        this.mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/admin/api/v1")
//                .with(httpBasic("anon@mail.ru", "anon")))
//                .andExpect(status().isUnauthorized());
//    }
//}
