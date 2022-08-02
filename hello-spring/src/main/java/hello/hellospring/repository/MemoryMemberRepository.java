package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    //필드
    private static Map<Long, Member> store = new HashMap<>();   //회원 정보들을 모두 저장할 HashMap
    private static long sequence = 0L;  //ID 생성용 변수. 0L의 L은 명시적으로 long임을 나타냄


    //메소드
    @Override
    public Member save(Member member) {
        member.setId(++sequence);   //먼저 저장되는 순서대로 1씩 증가하는 ID를 부여받음
        store.put(member.getId(), member);  //store에 저장하기
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()  //stream(): store의 value들을 모두 싹 돌면서
                .filter(member -> member.getName().equals(name))    //filter() 내의 기준에 부합하는 것이
                .findAny(); //하나라도 발견되면 바로 그것을 리턴
        //만약 store의 value들을 끝까지 다 살폈는데 없었다면 Optional에 감싸진 null이 리턴됨
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
