package com.so.controllers;

import com.so.dtos.AnswerDetails;
import com.so.dtos.AnswerDetailsDto;
import com.so.dtos.SearchResultDto;
import com.so.enums.EntityType;
import com.so.enums.VoteType;
import com.so.exceptions.NotFoundException;
import com.so.models.Answer;
import com.so.models.Comment;
import com.so.models.Question;
import com.so.models.QuestionTag;
import com.so.models.Tags;
import com.so.models.Vote;
import com.so.services.answer.AnswerService;
import com.so.services.comment.CommentService;
import com.so.services.question.QuestionService;
import com.so.services.tags.QuestionTagService;
import com.so.services.tags.TagsService;
import com.so.services.vote.VoteService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/search")
@AllArgsConstructor
public class SearchController {

    private final AnswerService answerService;
    private final QuestionService questionService;
    private final VoteService voteService;
    private final QuestionTagService questionTagService;
    private final CommentService commentService;
    private final ModelMapper modelMapper;

//    @GetMapping("/question")
//    @ResponseBody
//    public List<Answer> searchQuestion(@RequestParam("title") String title, @RequestParam("page") int page,
//                                       @RequestParam("size") int size) {
//        Optional<Question> question = answerService.
//       Optional<Long> upvoteCounts = voteService.countVoteByQuestionId();
//
//        return Collections.emptyList();
//    }

    @GetMapping("/question/{questionId}")
    @ResponseBody
    public SearchResultDto searchQuestion(@PathVariable Long questionId, @RequestParam("page") int page, @RequestParam("size") int size) {
        Optional<Question> question = questionService.findById(questionId);
        if (question.isEmpty())
            throw new NotFoundException("question does not exist on id " + questionId);
        SearchResultDto.SearchResultDtoBuilder searchResultDto = SearchResultDto.builder();

        Optional<Long> upvoteCounts = voteService.countVoteByQuestionId(questionId, VoteType.UPVOTE);
        if(upvoteCounts.isEmpty())
            upvoteCounts = Optional.of(0L);
        searchResultDto.upVoteCount(upvoteCounts.get());
        searchResultDto.question(question.get());

        // finding QuestionTag
        List<QuestionTag> questionTags = questionTagService.getQuestionTagsByQuestionsId(questionId);

        List<Tags> tags = questionTags.stream().map(QuestionTag::getTagId).collect(Collectors.toList());
        searchResultDto.questionTags(tags);
        // Finding Comment on question
        List<Comment> questionComments = commentService.getCommentByEntityIdAndEntityType(questionId, EntityType.QUESTION);
        //
        searchResultDto.questionComment(questionComments);

        // finding answers for this question
        List<Answer> answers = answerService.getAnswersByQuestionIdAndPage(questionId, size, page);
        List<AnswerDetails> answerDetails = new ArrayList<>();
        AnswerDetails answerDetail = null;
        Map<Long, AnswerDetails> answerDetailsMap = new HashMap<>();

        for(Answer answer: answers){
            answerDetail = modelMapper.map(answer, AnswerDetails.class);
            answerDetailsMap.put(answer.getId(), answerDetail);
        }
        List<Comment> comments = (List<Comment>) commentService.getCommentsByEntityIds(answerDetailsMap.keySet());

        Map<Long, List<Comment>> entityIdAndCommentMap = new HashMap<>();
        for(Comment comment: comments){
            List<Comment> com = entityIdAndCommentMap.getOrDefault(comment.getEntityId(), new ArrayList<>());
            com.add(comment);
            entityIdAndCommentMap.put(comment.getEntityId(), com);
        }
        for(Long entityId : answerDetailsMap.keySet()){
            AnswerDetails details = answerDetailsMap.get(entityId);
            details.setComments(entityIdAndCommentMap.get(entityId));
            answerDetails.add(details);
        }

        searchResultDto.answers(new AnswerDetailsDto(answerDetails));
        return searchResultDto.build();
    }
}
