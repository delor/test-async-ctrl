package example.test_async_ctrl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ExampleControllerTest {

    MockMvc mvc;

    @BeforeEach
    void setUp() {
        ExampleService service = Mockito.mock(ExampleService.class);
        given(service.getExample())
                .willReturn(new Example("example"));
        ExecutorService executor = Executors.newSingleThreadExecutor();
        ExampleController exampleController = new ExampleController(service, executor);
        mvc = MockMvcBuilders.standaloneSetup(exampleController)
                .build();
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
