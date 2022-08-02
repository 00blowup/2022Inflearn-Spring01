package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    MemberRepository memrepo;
    MemberService memser;

    @BeforeEach
    public void beforeEach() {
        memrepo = new MemoryMemberRepository();
        memser = new MemberService(memrepo);
    }

    @Test
    void join() {
        //given
        Member mem1 = new Member();
        mem1.setName("Apple");

        //when
        Long joinedId = memser.join(mem1);

        //then
        Member foundMem = memser.findOne(joinedId).get();
        assertThat(foundMem).isEqualTo(mem1);
    }

    @Test
    void duplicatedName(){
        Member mem1 = new Member();
        mem1.setName("Banana");
        Member mem2 = new Member();
        mem2.setName("Banana");

        memser.join(mem1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memser.join(mem2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");
    }

}