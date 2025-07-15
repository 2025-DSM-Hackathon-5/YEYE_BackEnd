package org.example.yeye_backend.domain.user.domain.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.domain.user.domain.repository.UserRepository;
import org.example.yeye_backend.domain.user.domain.service.CommandUserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandUserServiceImpl implements CommandUserService {
    private final UserRepository userRepository;

    @Override
    public void deleteUserByUserId(Long id) {
        userRepository.deleteById(id);
    }
}
