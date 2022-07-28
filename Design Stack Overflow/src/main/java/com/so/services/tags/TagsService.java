package com.so.services.tags;

import com.so.models.Tags;
import com.so.services.BaseService;
import com.so.vos.TagVo;

import java.util.List;
import java.util.Optional;

public interface TagsService extends BaseService<Tags> {
    Optional<TagVo> createTags(TagVo tagVo);
    Optional<TagVo> searchTags(String title);
    List<TagVo> getAll();
    List<Tags> findByTagIds(List<Long> id);
}

