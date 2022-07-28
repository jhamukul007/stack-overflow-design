package com.so.services.tags.impl;

import com.so.models.Tags;
import com.so.repo.question.TagRepo;
import com.so.services.tags.TagsService;
import com.so.vos.TagVo;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class TagsServiceImpl implements TagsService {

    private final TagRepo tagRepo;
    private final ModelMapper modelMapper;

    @Override
    public Tags saveOrUpdateTx(Tags value) {
        return tagRepo.save(value);
    }

    @Override
    public Optional<Tags> findById(@NonNull Long id) {
        return tagRepo.findById(id);
    }

    @Override
    public Optional<TagVo> createTags(TagVo tagVo) {
        Tags tag = modelMapper.map(tagVo, Tags.class);
        saveOrUpdateTx(tag);
        tagVo.setId(tag.getId());
        return Optional.ofNullable(tagVo);
    }

    @Override
    public Optional<TagVo> searchTags(String title) {
        Optional<Tags> tagOp = tagRepo.findByTitle(title);
        if (tagOp.isEmpty()) {
            log.warn("tag does not exist on title {} ", title);
            return Optional.empty();
        }
        Tags tag = tagOp.get();
        return Optional.of(modelMapper.map(tag, TagVo.class));
    }

    @Override
    public List<TagVo> getAll() {
        return tagRepo.findAll().stream().map(tag ->
                modelMapper.map(tag, TagVo.class)).collect(Collectors.toList());
    }

    @Override
    public List<Tags> findByTagIds(List<Long> id) {
        return tagRepo.findAllById(id);
    }

    @Override
    public <S extends Tags> List<S> saveAll(List<S> entities) {
        return tagRepo.saveAll(entities);
    }
}
