package spring3_4_5.boot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring3_4_5.boot.service.AOPService;

@RestController
@RequestMapping("/aop")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AOPController {

    private final AOPService aopService;

    // parameter x
    @GetMapping("/test")
    public String checkTime() {
        return aopService.test("바이바이");
    }

    // parameter o
    @GetMapping("/test2")
    public String checkTime(@RequestParam(name = "query") String query) {
        return aopService.test(query);
    }

}
