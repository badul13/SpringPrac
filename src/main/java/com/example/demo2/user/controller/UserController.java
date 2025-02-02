package com.example.demo2.user.controller;

import com.example.demo2.base.constant.Code;
import com.example.demo2.base.dto.DataResponseDTO;
import com.example.demo2.base.dto.ResponseDTO;
import com.example.demo2.user.dto.UserDTO;
import com.example.demo2.user.dto.UserIDRequestDTO;
import com.example.demo2.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

//    @PostMapping
//    public ResponseDTO createUser(@RequestBody UserDTO userDto) {
//        userService.createUser(userDto);
//        return ResponseDTO.of(true, Code.OK);
//    }
    // 얘는 data 제외하고 그냥 success code message만 보여주는 거
    // data도 보여주려면 ResponceEntity를 사용해야 함

    @PostMapping
    public ResponseEntity<DataResponseDTO<UserDTO>> createUser(@Valid @RequestBody UserDTO userDto) {
        UserDTO createdUser = userService.createUser(userDto);
        return ResponseEntity.ok(DataResponseDTO.of(createdUser));
    }

    @PostMapping("/finduser")
    public DataResponseDTO<UserDTO> getUser(@RequestBody UserIDRequestDTO request) {
        return DataResponseDTO.of(userService.getUser(request.getId()));
    }
    //

    @GetMapping("/alluser")
    public ResponseEntity<DataResponseDTO<List<UserDTO>>> getAllUsers() {
        return ResponseEntity.ok(DataResponseDTO.of(userService.getAllUsers()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponseDTO<UserDTO>> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDto) {
        UserDTO updatedUser = userService.updateUser(id, userDto);
        return ResponseEntity.ok(DataResponseDTO.of(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseDTO deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseDTO.of(true, Code.OK);
    }
    //얘는 반환값 없으니까 이렇게..?

    //put, delete는 찾아보니까 post 안 쓰는 게 더 나은 것 같다는 것 같아서 이렇게 했는데
    //솔직히 아직 안 와닿아서 잘 모르겠음 좀 더 찾아보고 수정함
    //로그인아이디가 아니라 이런 얘들은 그냥 주소에 노출되어도 상관없을 것 같아서 일단 이렇게 했습니다




}