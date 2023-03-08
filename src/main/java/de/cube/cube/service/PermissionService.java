package de.cube.cube.service;

import de.cube.cube.database.entity.Permission;
import de.cube.cube.database.repository.PermissionRepository;
import de.cube.cube.domain.PermissionDto;
import de.cube.cube.domain.dto.create.PermissionCreateDto;
import de.cube.cube.domain.dto.delete.PermissionDeleteDto;
import de.cube.cube.domain.dto.update.PermissionUpdateDto;
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
public class PermissionService implements Crud<PermissionDto, PermissionCreateDto, PermissionUpdateDto, PermissionDeleteDto> {

    private final PermissionRepository permissionRepository;

    @Override
    public Optional<PermissionDto> create(PermissionCreateDto create) {
        Permission permission = this.save(Permission.builder().name(create.getName()).build());
        if (permission != null) {
            return Optional.of(PermissionDto.map(permission));
        }
        return Optional.empty();
    }

    @Override
    public Optional<PermissionDto> update(PermissionUpdateDto update) {
        Optional<Permission> optionalPermission = this.permissionRepository.findById(update.getId());
        if (optionalPermission.isPresent()) {
            optionalPermission.get().setName(update.getName());
            Permission permission = this.save(optionalPermission.get());
            if (permission != null) {
                return Optional.of(PermissionDto.map(permission));
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(PermissionDeleteDto delete) {
        return this.delete(delete.getId());
    }

    @Override
    public Optional<PermissionDto> findById(long id) {
        Optional<Permission> optionalPermission = this.permissionRepository.findById(id);
        return optionalPermission.map(PermissionDto::map);
    }

    @Override
    public List<PermissionDto> findAll(Sort sort) {
        List<Permission> permissions = this.permissionRepository.findAll(sort);
        List<PermissionDto> permissionDtos = new ArrayList<>();
        for (Permission permission : permissions) {
            permissionDtos.add(PermissionDto.map(permission));
        }
        return permissionDtos;
    }

    private boolean delete(long id) {
        try {
            this.permissionRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            log.error("can not delete permission. " + e);
        }
        return Boolean.FALSE;
    }

    private Permission save(Permission insert) {
        try {
            return this.permissionRepository.save(insert);
        } catch (Exception e) {
            log.error("can not store permission. " + e);
        }
        return null;
    }

}
