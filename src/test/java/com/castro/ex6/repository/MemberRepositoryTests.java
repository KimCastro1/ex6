package com.castro.ex6.repository;

import com.castro.ex6.entity.Member;
import com.castro.ex6.repositroy.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;
    @Test
    public void insertMember(){
        IntStream.rangeClosed(1,100).forEach(i->{
            Member member = Member.builder()
                    .email("testId"+i+"@gmail.com")
                    .pw("1234")
                    .nickname("TestNickName"+i)
                    .build();
            memberRepository.save(member);
        });
    }
}
