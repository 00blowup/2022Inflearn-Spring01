package hello.hellospring.domain;

public class Member {

    private Long id;    //고객이 지정해서 로그인할 때 쓰는 그 ID가 아니고, 시스템이 고객 구별을 위해 부여하는 고유값 ID임!
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
