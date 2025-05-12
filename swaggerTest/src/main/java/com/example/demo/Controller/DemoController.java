package com.example.demo.Controller;

import com.example.demo.Model.DAO.MemoDAO;
import com.example.demo.Model.DTO.MemoSaveDTO;
import com.example.demo.Repository.MemoRepository;
import com.example.demo.Service.MemoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.models.Components;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "Demo", description = "Demo API")
public class DemoController {

    private final MemoRepository memoRepository;
    private final MemoService memoService;

    // 데이터 전달에는 json, get(쿼리파라미터), post(form-data)가 있다.
    // TODO 컨트롤러에서 RequestMapping 메서드에서는 개별적으로 받는 방법

    @GetMapping("/memo")
    public List<MemoDAO> demo() {
        return memoRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));

    }

    @PostMapping("/memo")
    public void create(@RequestBody MemoSaveDTO dto){
        memoService.create(dto);
    }

    @Operation(summary = "test swagger", description = "swagger 테스트 출력")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "테스트 성공"),
            @ApiResponse(responseCode = "409", description = "테스트 실패")
    })
    @GetMapping("/swagger")
    public String swaggerTest() {
        return "Hello Swagger!";
    }
}
