package com.so.services.tags.impl;

import com.so.models.Question;
import com.so.models.QuestionTag;
import com.so.models.Tags;
import com.so.repo.question.QuestionTagRepo;
import com.so.services.tags.QuestionTagService;
import com.so.services.tags.TagsService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class QuestionTagServiceImpl implements QuestionTagService {

    private final QuestionTagRepo questionTagRepo;
    private final ModelMapper modelMapper;
    private final TagsService tagsService;

    @Override
    public Optional<QuestionTag> findById(@NonNull Long id) {
        return Optional.empty();
    }

    @Override
    public List<QuestionTag> saveQuestionTag(Question question, List<Long> tagIds) {
        List<Tags> tags = tagsService.findByTagIds(tagIds);
        List<QuestionTag> questionTags = tags.stream().map(tag ->
        {
            QuestionTag qTag = new QuestionTag();
            qTag.setQuestionId(question);
            qTag.setTagId(tag);
            return qTag;
        }).collect(Collectors.toList());
        return saveOrUpdateAll(questionTags);
    }

    @Override
    public List<QuestionTag> getQuestionTagsByQuestionsId(List<Long> questionIds) {
        return questionTagRepo.findByQuestionIdIn(questionIds);
    }

    @Override
    public List<QuestionTag> getQuestionTagsByQuestionsId(@NonNull Long questionId) {
        return questionTagRepo.findByQuestionId(questionId);
    }

    @Override
    public List<QuestionTag> findAll() {
        return questionTagRepo.findAll();
    }

    @Override
    public QuestionTag saveOrUpdate(QuestionTag value) {
        return questionTagRepo.save(value);
    }

    public List<QuestionTag> saveOrUpdateAll(List<QuestionTag> values) {
        return questionTagRepo.saveAll(values);
    }
}
