package com.so.controllers;

import com.so.enums.QuestionStatus;
import com.so.models.Badges;
import com.so.models.Members;
import com.so.models.Question;
import com.so.models.Tags;
import com.so.services.badges.BadgesService;
import com.so.services.member.MemberService;
import com.so.services.question.QuestionService;
import com.so.services.tags.QuestionTagService;
import com.so.services.tags.TagsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/data/fill")
@AllArgsConstructor
public class DataFillController {

    private final TagsService tagsService;
    private final QuestionService questionService;
    private final QuestionTagService questionTagService;
    private final MemberService memberService;
    private final BadgesService badgesService;

    @PostMapping("/tags")
    public String createTags() {

        Tags tag = new Tags();
        tag.setTitle("java");
        tag.setDescription("programming language");

        Tags tag1 = new Tags();
        tag1.setTitle("design patterns");
        tag1.setDescription("programming language");

        Tags tag2 = new Tags();
        tag2.setTitle("scala");
        tag2.setDescription("scala is a programming language");

        Tags tag3 = new Tags();
        tag3.setTitle("kafka");
        tag3.setDescription("messaging queue");

        Tags tag4 = new Tags();
        tag4.setTitle("docker");
        tag4.setDescription("Docker is container systeme");

        Tags tag5 = new Tags();
        tag5.setTitle("mysql");
        tag5.setDescription("RDBMS");

        List<Tags> tags = new ArrayList<>();
        tags.add(tag);
        tags.add(tag1);
        tags.add(tag2);
        tags.add(tag3);
        tags.add(tag4);
        tagsService.saveAll(tags);
        return "DONE";
    }

    @PostMapping("/members")
    public String createMember() {
        return "";
    }

    @PostMapping("/questions")
    public String createQuestion() {
        Question question = new Question();
        question.setTitle("What is core pool size in thread executor ?");
        question.setDescription("I want to create thread pool executor for highly scaled system spring boot");

        question.setMember(memberService.findById(1L).get());
        question.setQuestionStatus(QuestionStatus.OPEN);

        Question question1 = new Question();
        question1.setTitle("Permission denied while uploading file on s3");
        question1.setDescription("Exception details");
        question1.setMember(memberService.findById(2L).get());
        question1.setQuestionStatus(QuestionStatus.OPEN);
        List<Question> questions = questionService.saveAll(List.of(question, question1));
        questions.forEach(que ->
                questionTagService.saveQuestionTag(que, List.of(1L,2L,3L)));
        return "DONE";
    }


    @PostMapping("/all")
    public String createDummyData() {
        addBadges();
        createTags();
        List<Members> members = createMembers();
        createQuestion(members);
        return "DONE";
    }

    List<Members> createMembers(){
        Members member = new Members();
        member.setName("Mukul Jha");
        member.setEmailId("muk.jha@bb.com");
        member.setUserId("jhamukul.stackoverflow.com");

        Members member1= new Members();
        member1.setName("Rahul Jha");
        member1.setEmailId("rahul.jha@bb.com");
        member1.setUserId("rahuljha.stackoverflow.com");

        memberService.saveAll(List.of(member, member1));
        return List.of(member, member1);
    }


    void createQuestion(List<Members> members){
        Question question = new Question();
        question.setTitle("What is core pool size in thread executor ?");
        question.setDescription("I want to create thread pool executor for highly scaled system spring boot");

        question.setMember(members.get(0));
        question.setQuestionStatus(QuestionStatus.OPEN);

        Question question1 = new Question();
        question1.setTitle("Permission denied while uploading file on s3");
        question1.setDescription("Exception details");
        question1.setMember(members.get(1));
        question1.setQuestionStatus(QuestionStatus.OPEN);
        List<Question> questions = questionService.saveAll(List.of(question, question1));
        questions.forEach(que ->
                questionTagService.saveQuestionTag(que, List.of(1L,2L,3L)));
    }

    void addBadges(){
        Badges b1 = new Badges();
        b1.setTitle("Gold");
        b1.setRanking(5);
        b1.setStartPoint(500);
        b1.setStartPoint(2500);

        Badges b2 = new Badges();
        b2.setTitle("Bronze");
        b2.setRanking(1);
        b2.setStartPoint(50);
        b2.setStartPoint(125);

        Badges b3 = new Badges();
        b3.setTitle("Bronze");
        b3.setRanking(1);
        b3.setStartPoint(126);
        b3.setStartPoint(499);

        badgesService.saveAll(List.of(b1, b2, b3));
    }
}
