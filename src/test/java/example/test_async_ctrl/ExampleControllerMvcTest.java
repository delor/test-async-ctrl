package example.test_async_ctrl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({ExampleController.class})
class ExampleControllerMvcTest {

    @Autowired MockMvc mvc;
    @MockBean ExampleService service;

    @BeforeEach
    void setUp() {
        given(service.getExample())
                .willReturn(new Example("example"));
    }

    @Test
    void getAsync() throws Exception {
        mvc.perform(get("/async-example"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("example"));
    }

    @Test
    void getSync() throws Exception {
        mvc.perform(get("/example"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("example"));
    }
}
