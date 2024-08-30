package hello.hello_spring.Repository;

import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //null일 경우 반환 처리
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
