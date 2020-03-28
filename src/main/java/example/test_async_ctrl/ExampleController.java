package example.test_async_ctrl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class ExampleController {

    ExampleService service;
    Executor executor;

    @GetMapping("/async-example")
    CompletableFuture<Example> getAsyncExample() {
        return CompletableFuture.supplyAsync(service::getExample, executor);
    }

    @GetMapping("/example")
    Example getExample() {
        return service.getExample();
    }
}
