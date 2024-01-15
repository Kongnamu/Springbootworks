<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
</head>
<body>
	<h2>글쓰기</h2>
	<table>
		<tbody>
			<tr>
				<td>
					<input type="text" name="title"
						placeholder="글제목" required>제목
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" name="writer"
						placeholder="작성자" required>작성자
				</td>
			</tr>
			<tr>
				<td>
					<textarea rows="5" cols="60"
							name="content" required></textarea>
				</td>
			</tr>
			<tr>
				<td>
				<input type="submit" value="등록">
				<input type="reset" value="취소">
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>