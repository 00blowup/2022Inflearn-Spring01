package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberRepository memrepo;
    @Autowired MemberService memser;

    @Test
    void join() {
        //given
        Member mem1 = new Member();
        mem1.setName("Apple");

        //when
        Long joinedId = memser.join(mem1);

        //then
        Member foundMem = memser.findOne(joinedId).get();
        assertThat(foundMem.getName()).isEqualTo(mem1.getName());
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