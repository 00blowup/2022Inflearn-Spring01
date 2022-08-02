package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repo = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repo.clearStore();
    }

    @Test
    public void save(){
        Member member1 = new Member();
        member1.setName("member1");
        repo.save(member1);

        Member result = repo.findById(member1.getId()).get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findByName(){
        Member m1 = new Member();
        m1.setName("Hello");
        repo.save(m1);

        Member m2 = new Member();
        m2.setName("Bye");
        repo.save(m2);

        Member result = repo.findByName("Hello").get();

        assertThat(result).isEqualTo(m1);
    }

    @Test
    public void findAll(){
        Member m1 = new Member();
        m1.setName("Hello");
        repo.save(m1);

        Member m2 = new Member();
        m1.setName("Bye");
        repo.save(m2);

        List<Member> result = repo.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

}
