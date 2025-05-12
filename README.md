# study_spring
inflearn spring lecture practice

# swaggerTest Project 

[swaggerTest](https://github.com/Euihyunee/study_spring/tree/main/swaggerTest) 프로젝트는 swagger를 테스트 해본 것입니다.

### 의존성 추가

```groovy
dependencies {
    implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0'
}
```

### application.yml 설정

```yaml
springdoc:
  api-docs:
    path: /api-docs  # OpenAPI JSON 문서 경로를 정의합니다.
  default:
    consumes-media-type: application/json  # 요청 본문의 기본 미디어 타입을 설정합니다.
    produces-media-type: application/json  # 응답 본문의 기본 미디어 타입을 설정합니다.
  swagger-ui:
    disable-swagger-default-url: true  # 기본 Swagger URL을 비활성화합니다.
    doc-expansion: none  # Swagger UI 문서의 기본 확장 설정을 제어합니다.
    operations-sorter: alpha  # Swagger UI에서 작업(Operations)의 정렬 순서를 지정합니다.
    path: /swagger-ui.html  # Swagger UI에 접근할 경로를 정의합니다.
    tags-sorter: alpha  # Swagger UI에서 태그(Tags)의 정렬 순서를 지정합니다.
```

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) 에서 Swagger UI를 확인할 수 있습니다.

### API 명세 작성하기 

```java
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        // Info 객체를 생성하여 API 문서의 기본 정보를 설정합니다.
        Info info = new Info()
                .title("My API")  // API 제목
                .version("v1.0")  // API 버전
                .description("API Documentation");  // API 설명

        // OpenAPI 객체를 반환하며, Components 및 Info를 설정합니다.
        return new OpenAPI()
                .components(new Components())  // 추가적인 컴포넌트(예: 보안 스키마)를 설정할 수 있음
                .info(info);  // 위에서 정의한 Info 객체를 추가
    }
}
```

주요 기능:

`@Configuration`: Spring에서 이 클래스를 설정 파일로 인식하도록 지정합니다.

`@Bean`: Spring 컨텍스트에 OpenAPI 객체를 등록합니다.

`Info`: OpenAPI 문서의 제목, 버전, 설명을 설정하는 객체입니다.

`Components`: 인증 스키마나 기타 확장 가능한 컴포넌트를 추가할 수 있습니다.

```java
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User", description = "User API")
public interface UserController {

    @Operation(summary = "유저 정보 저장", description = "유저 정보를 저장합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저 정보 저장 성공"),
            @ApiResponse(responseCode = "409", description = "유저 정보 저장 실패(유저 중복)")
    })
    public ResponseEntity<String> saveUser(@RequestBody UserEntity user);
}
```

`@Tag`
역할: API 그룹을 정의합니다.

예시:

```java
@Tag(name = "User", description = "User API")
```

`name`: 태그 이름으로 Swagger UI에서 그룹화됩니다.

`description`: 태그에 대한 설명입니다.

`@Operation`
역할: 특정 엔드포인트(메서드)에 대한 요약 및 상세 설명을 정의합니다.

예시:

```java
@Operation(summary = "유저 정보 저장", description = "유저 정보를 저장합니다.")
```

`summary`: 간단한 요약을 제공합니다.

`description`: 엔드포인트의 상세 설명을 제공합니다.

`@ApiResponses`
역할: 메서드가 반환할 수 있는 여러 응답 코드를 정의합니다.

예시:

```java
@ApiResponses(value = {
@ApiResponse(responseCode = "200", description = "유저 정보 저장 성공"),
@ApiResponse(responseCode = "409", description = "유저 정보 저장 실패(유저 중복)")
})
```

`responseCode`: HTTP 응답 상태 코드(예: 200, 409).

`description`: 해당 응답 코드에 대한 설명.

`@RequestBody`
역할: 요청 본문을 매핑하며, 클라이언트에서 전달되는 데이터를 처리하기 위한 어노테이션입니다.

예시:

```java
public ResponseEntity<String> saveUser(@RequestBody UserEntity user);
```

UserEntity user: 클라이언트가 전송한 JSON 데이터를 Java 객체로 변환하여 처리합니다.
