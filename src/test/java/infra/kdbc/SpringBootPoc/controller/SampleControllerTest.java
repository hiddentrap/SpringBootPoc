package infra.kdbc.SpringBootPoc.controller;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SampleController.class)
@Log4j2
class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("HelloWorld 리턴 테스트")
    void helloWorldTest() throws Exception{
        mockMvc.perform(get("/hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("HelloWorld"));
        log.info("롬복 로거 테스트");
    }

    @Test
    void getEventTest() throws Exception {
        mockMvc.perform(get("/events/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1));
    }
}