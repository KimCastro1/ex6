package com.castro.ex6.repositroy;

import com.castro.ex6.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
