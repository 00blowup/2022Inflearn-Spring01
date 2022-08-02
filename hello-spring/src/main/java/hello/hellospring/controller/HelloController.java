package hello.hellospring.controller;
//필요한 것들 import 잊지 않기
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller     //이 참조는 꼭 필수!!!
public class HelloController {

    //단순하게 static html을 띄우는 함수
    @GetMapping("hello")    //이 참조문은 "/hello"라는 URL 경로가 전달되면 아래의 함수가 실행되도록 해준다고 함
    public String hello(Model model){   //모델을 인자로 받아 문자열을 리턴하는 함수
        model.addAttribute("data", "와우");  //모델에다가 data라는 어트리뷰트를 추가하고 그 내용을 지정해주기
        return "hello"; //hello라는 문자열을 리턴하면, 자동으로 templates 하위의 hello.html이라는 파일을 찾아가 실행함!!! 신기하다
    }

    //MVC와 템플릿 엔진을 사용해서 html의 내용을 동적으로 바꿔 띄우는 함수
    @GetMapping("MVC-ex")
    public String mvcEx(@RequestParam("name") String name, Model model){    //name이라는 파라미터를 요청하여 받아온 문자열 값 name,그리고 모델 model을 인자로 사용
        model.addAttribute("name", name);
        return "mvcEx"; //위의 hello 함수와 비슷하게, 얘는 mvcEx.html을 찾아가도록 함
    }

    //API(문자열을 반환하는 방식)
    @GetMapping("API-string-ex")
    @ResponseBody
    public String apiStringEx(@RequestParam("name") String name){
        return "hello " + name;
    }

    //API(객체를 반환하는 방식)
    @GetMapping("API-object-ex")
    @ResponseBody
    public Hello apiObjectEx(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    public class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
