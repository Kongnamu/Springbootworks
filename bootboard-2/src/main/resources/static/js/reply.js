/**
 * 댓글 구현
 */

let replyObject = {
	
	init: function(){
		
		$("#btn-save-reply").click(() => {
			this.insertReply(); //this : 클릭한 대상
		});
	},
		
	insertReply: function(){
		alert("댓글 등록 요청");
		
		let boardId = $("#boardId").val();
		//document.getElementById(replyContent).value와 같음
		let replyContent = $("#replyContent").val();
		
		if(replyContent == ""){
			alert("댓글을 입력해주세요");
			$("#replyContent").focus();
			return false;
		}
		let reply = {
			content: replyContent //content : 컨트롤러로 넘겨주는 데이터
		}
		console.log(reply);
		
		let header = $("meta[name='_csrf_header']").attr('content');
		let token = $("meta[name='_csrf']").attr('content');
		
		$.ajax({
			type:"POST",
			beforeSend: function(xhr){
		        xhr.setRequestHeader(header, token);
		    },
			url: "/reply/" + boardId,
			data:JSON.stringify(reply), //자바 스크립트 객체(reply)를 문자화(json)
			contentType: "application/json; charset=utf-8"
		}).done(function(response){
			console.log(response);
			replyContent = "";
			location.href = "/board/" + boardId;
		}).fail(function(error){
			alert("에러 발생: " + error);
		});
	}, //insertReply 닫기
	
	deleteReply: function(boardId, replyId){
		alert("댓글 삭제 요청됨")
		
		let header = $("meta[name='_csrf_header']").attr('content');
		let token = $("meta[name='_csrf']").attr('content');
		
		$.ajax({
			type: "DELETE",
			beforeSend: function(xhr){
		        xhr.setRequestHeader(header, token);
		    },
			url: "/reply/" + replyId
		}).done(function(response){
			console.log(response);
			location.href = "/board/" + boardId;
		}).fail(function(error){
			alert("에러 발생: " + error);
		});
	}
}

 replyObject.init(); //init 함수 호출