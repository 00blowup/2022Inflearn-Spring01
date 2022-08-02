package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;


    //외부에서 MemberRepository를 받아오는 생성자
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member){

        duplicatedNameCheck(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void duplicatedNameCheck(Member member) {
        //이름 중복 검사하기
        memberRepository.findByName(member.getName()).ifPresent(m ->
                {
                    throw new IllegalStateException("이미 존재하는 이름입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * ID로 회원 조회
     */
    public Optional<Member> findOne(Long id){
        return memberRepository.findById(id);
    }
}
