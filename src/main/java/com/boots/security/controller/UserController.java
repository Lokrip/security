package com.boots.security.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boots.security.dto.UserDto;
import com.boots.security.dto.response.MessageDto;
import com.boots.security.entity.UserEntity;
import com.boots.security.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

//@Tag тут мы указываем имя загаловка для всех методов контроллера
//можно еще укозать описание
@Tag(name = "User Controller")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //@Operation — это аннотация из OpenAPI (Swagger), используемая в Spring Boot для документирования API-эндпоинтов.
    //summary тут мы пишем краткое содержание
    //description тут мы пишем описание
    //responses тут мы пишем какой будет ответ
    @Operation(
        summary = "user.controller.list.summary",
        description = "user.controller.list.description",
        responses = {
            @ApiResponse(responseCode = "200", description = "Запрос успешно обработан")
        }
    )
    @GetMapping("/list")
    public ResponseEntity<MessageDto> usersList() {
        MessageDto messageDto = new MessageDto("Список пользователей");
        return new ResponseEntity<>(messageDto, HttpStatus.ACCEPTED);
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> userCreate(@RequestBody UserEntity userEntity) {
        UserDto userDto = userService.createUser(userEntity);
        URI location = URI.create("/api/v1/users/list");
        return ResponseEntity.created(location).body(userDto);
    }
}
