package com.boots.security.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boots.security.dto.UserDto;

import com.boots.security.entity.UserEntity;
import com.boots.security.exeption.UserNotCreatedException;
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
    public ResponseEntity<List<UserEntity>> usersList() {
        List<UserEntity> userEntities = userService.allUsers();
        return new ResponseEntity<>(userEntities, HttpStatus.ACCEPTED);
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> userCreate(@RequestBody UserDto userDto) throws UserNotCreatedException {
        UserDto userDtoResult = userService.createUser(userDto);
        return new ResponseEntity<>(userDtoResult, HttpStatus.OK);
    }
}
