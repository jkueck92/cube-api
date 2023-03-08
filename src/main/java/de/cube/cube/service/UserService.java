package de.cube.cube.service;

import de.cube.cube.database.entity.User;
import de.cube.cube.database.repository.UserRepository;
import de.cube.cube.domain.UserDto;
import de.cube.cube.domain.dto.create.UserCreateDto;
import de.cube.cube.domain.dto.delete.UserDeleteDto;
import de.cube.cube.domain.dto.update.UserUpdateDto;
import de.cube.cube.service.interfaces.Crud;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements Crud<UserDto, UserCreateDto, UserUpdateDto, UserDeleteDto> {

    private final UserRepository userRepository;

    @Override
    public Optional<UserDto> create(UserCreateDto create) {
        User user = this.save(User.builder().firstName(create.getFirstName()).lastName(create.getLastName()).email(create.getEmail()).password(create.getPassword()).isDisabled(create.isDisabled()).build());
        if (user != null) {
            return Optional.of(UserDto.map(user));
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserDto> update(UserUpdateDto update) {
        Optional<User> optionalUser = this.userRepository.findById(update.getId());
        if (optionalUser.isPresent()) {
            optionalUser.get().setFirstName(update.getFirstName());
            optionalUser.get().setLastName(update.getLastName());
            optionalUser.get().setEmail(update.getEmail());
            optionalUser.get().setPassword(update.getPassword());
            optionalUser.get().setDisabled(update.isDisabled());
            User user = this.save(optionalUser.get());
            if (user != null) {
                return Optional.of(UserDto.map(user));
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(UserDeleteDto delete) {
        return this.delete(delete.getId());
    }

    @Override
    public Optional<UserDto> findById(long id) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        return optionalUser.map(UserDto::map);
    }

    @Override
    public List<UserDto> findAll(Sort sort) {
        List<User> users = this.userRepository.findAll(sort);
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(UserDto.map(user));
        }
        return userDtos;
    }

    private boolean delete(long id) {
        try {
            this.userRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            log.error("can not delete user. " + e);
        }
        return Boolean.FALSE;
    }

    private User save(User insert) {
        try {
            return this.userRepository.save(insert);
        } catch (Exception e) {
            log.error("can not store user " + e);
        }
        return null;
    }

}
