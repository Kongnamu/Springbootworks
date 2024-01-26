package com.khit.board.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.khit.board.entity.Users;

@RestController //데이터 전달을 역할로 하는 어노테이션(객체도 반환 가능)
public class SampleController {

	//get : select
	//객체를 반환하면 json 형태로 전달됨
	@GetMapping("/khit")
	public Users httpGet() {
		//user 1명을 생성한 후 데이터 검색(보기)
		//Users user = new Users();
		//user.setId(user.getId());
		Users user = Users.builder()
				.id(1)
				.username("뽀로로")
				.password("123")
				.email("play@play.com")
				.build();
		
		return user;
	}
	//post : insert
	//전달받은 데이터가 json 형태 ({key:value})일때 @RequestBody 사용
	@PostMapping("/khit")
	public String httpPost(@RequestBody Users users) {
		return "POST 요청 처리" + users.toString();
	}
	
	//put : update
	@PutMapping("/khit")
	public String httpPut(@RequestBody Users users) {
		return "put 요청 처리당" + users.toString();
	}
	
	//delete : delete
	//@PathVariable : 경로 변수를 전달 받음
	@DeleteMapping("/khit/{id}")
	public String httpDelete(@PathVariable Integer id) {
		return "delete 요청 처리용" + id;
	}
}
