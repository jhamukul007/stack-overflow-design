package com.so.services.badges.impl;

import com.so.models.Badges;
import com.so.repo.badges.BadgesRepository;
import com.so.services.badges.BadgesService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class BadgesServiceImpl implements BadgesService {
    private final BadgesRepository badgesRepository;

    @Override
    public Badges saveOrUpdate(Badges value) {
        return badgesRepository.save(value);
    }

    @Override
    public Badges saveOrUpdateTx(Badges value) {
        return badgesRepository.save(value);
    }

    @Override
    public Optional<Badges> findById(@NonNull Long id) {
        return badgesRepository.findById(id);
    }

    @Override
    public <S extends Badges> List<S> saveAll(List<S> entities) {
        return badgesRepository.saveAll(entities);
    }
}
