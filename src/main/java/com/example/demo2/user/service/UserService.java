package com.example.demo2.user.service;

import com.example.demo2.base.GeneralException;
import com.example.demo2.base.constant.Code;
import com.example.demo2.user.dto.UserDTO;
import com.example.demo2.user.entity.User;
import com.example.demo2.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDTO createUser(UserDTO userDto) {
        if (userDto.getName() == null || userDto.getEmail() == null) {
            throw new GeneralException(Code.VALIDATION_ERROR);
        }
        //일케해도안됨 이것도 좀더찾아봐야할듯

        try {
            User user = User.builder()
                    .name(userDto.getName())
                    .email(userDto.getEmail())
                    .build();
            userRepository.save(user);
            return new UserDTO(user);
        } catch (Exception e) {
            throw new GeneralException(Code.INTERNAL_ERROR);
        }
    }

    public UserDTO getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new GeneralException(Code.NOT_FOUND));
        return new UserDTO(user);
    }
    //얘네는 try-catch로 감싸면 무조건 감싼 게 뜸

//    public UserDTO updateUser(Long id, UserDTO userDto) {
//        User user = User.builder()
//                .name(userDto.getName())
//                .email(userDto.getEmail())
//                .build();
//        userRepository.save(user);
//        return userDto;
//    }
    //빌더 사용 방법: 만약 객체의 다른 필드를 변경하거나 새로운 객체로 교체해야 하는 상황이 아니라면, 굳이 빌더 패턴을 사용할 필요는 없다. 이 방법은 필요 이상으로 복잡할 수 있으며, 잘못된 필드 값을 복사하거나 누락할 위험이 있다.
    //즉, 빌더 패턴은 새로운 객체를 생성하거나 객체의 불변성을 유지해야 할 때 더 유용하다. 하지만 여기서는 기존 객체에 OAuth2 유저의 성별, 생일, 연락처의 정만 업데이트하는 것이므로 세터방식을 사용하는 것이 더 자연스럽고 안전하다.
    //근데 일케 안하면 세터 써야하는거아닌가..?
    //dirty checking? 이것도 해볼라했는데 좀 지금쓰기 애매한 것 같아 보여서 일단 그냥 이렇게 했음

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }
    //일단 이렇게 했는데 INTERNAL_ERROR 같은 건 이렇게 하나하나 다 해줘야하는건가 잘 모르겠음
    //이미 예외처리 있으면 꼬이는 것도 있고 이것도 찾아봐야함


    @Transactional
    public UserDTO updateUser(Long id, UserDTO userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new GeneralException(Code.NOT_FOUND));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        return userDto;
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new GeneralException(Code.NOT_FOUND);
        }
        userRepository.deleteById(id);
    }
}
