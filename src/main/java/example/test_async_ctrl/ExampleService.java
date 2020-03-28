package example.test_async_ctrl;

import org.springframework.stereotype.Service;

@Service
class ExampleService {

    Example getExample() {
        return new Example("example");
    }
}
