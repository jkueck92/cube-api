package de.cube.cube.service;

import de.cube.cube.database.entity.Role;
import de.cube.cube.database.repository.RoleRepository;
import de.cube.cube.domain.RoleDto;
import de.cube.cube.domain.dto.create.RoleCreateDto;
import de.cube.cube.domain.dto.delete.RoleDeleteDto;
import de.cube.cube.domain.dto.update.RoleUpdateDto;
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
public class RoleService implements Crud<RoleDto, RoleCreateDto, RoleUpdateDto, RoleDeleteDto> {

    private final RoleRepository roleRepository;

    @Override
    public Optional<RoleDto> create(RoleCreateDto create) {
        Role role = this.save(Role.builder().name(create.getName()).build());
        if (role != null) {
            return Optional.of(RoleDto.map(role));
        }
        return Optional.empty();
    }

    @Override
    public Optional<RoleDto> update(RoleUpdateDto update) {
        Optional<Role> optionalRole = this.roleRepository.findById(update.getId());
        if (optionalRole.isPresent()) {
            optionalRole.get().setName(update.getName());
            Role role = this.save(optionalRole.get());
            if (role != null) {
                return Optional.of(RoleDto.map(role));
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(RoleDeleteDto delete) {
        return this.delete(delete.getId());
    }

    @Override
    public Optional<RoleDto> findById(long id) {
        Optional<Role> optionalRole = this.roleRepository.findById(id);
        return optionalRole.map(RoleDto::map);
    }

    @Override
    public List<RoleDto> findAll(Sort sort) {
        List<Role> roles = this.roleRepository.findAll(sort);
        List<RoleDto> roleDtos = new ArrayList<>();
        for (Role role : roles) {
            roleDtos.add(RoleDto.map(role));
        }
        return roleDtos;
    }

    private boolean delete(long id) {
        try {
            this.roleRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            log.error("can not delete role. " + e);
        }
        return Boolean.FALSE;
    }

    private Role save(Role insert) {
        try {
            return this.roleRepository.save(insert);
        } catch (Exception e) {
            log.error("can not store role. " + e);
        }
        return null;
    }

}
