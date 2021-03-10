<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../part/mainLayoutHeader.jspf"%>

<script>
ArticleAdd__submited = false;
function ArticleAdd__checkAndSubmit(form) {
	if ( ArticleAdd__submited ) {
		alert('처리중입니다.');
		return;
	}
	
	form.title.value = form.title.value.trim();
	if ( form.title.value.length == 0 ) {
		alert('제목을 입력해주세요.');
		form.title.focus();
		return false;
	}
	form.body.value = form.body.value.trim();
	if ( form.body.value.length == 0 ) {
		alert('내용을 입력해주세요.');
		form.body.focus();
		return false;
	}
	var maxSizeMb = 50;
	var maxSize = maxSizeMb * 1024 * 1024;
	if (form.file__article__0__common__attachment__1.value) {
		if (form.file__article__0__common__attachment__1.files[0].size > maxSize) {
			alert(maxSizeMb + "MB 이하의 파일을 업로드 해주세요.");
			form.file__article__0__common__attachment__1.focus();
			
			return;
		}
	}

	form.submit();
	ArticleAdd__submited = true;
}
</script>

<section class="section-1">
	<div class="bg-white shadow-lg mt-4 container mx-auto rounded p-4">
		<div class="flex justify-center">
			<div class="text-4xl font-extrabold">게시물 작성</div>
		</div>
		<form onsubmit="ArticleAdd__checkAndSubmit(this); return false;"
			action="doAdd" method="post" enctype="multipart/form-data">
			<div class="flex m-4">
				<div class="flex items-center w-16">
					<span class="flex justify-center font-bold">게시판</span>
				</div>
				<div>
					<select class="py-2 select-board" name="boardCode"
						value="${boardCode}">
						<option value="1">공지사항</option>
						<option value="2">자유게시판</option>
					</select>
					<script>
						$('.section-1 .select-board').val(${boardCode});

					</script>
				</div>
			</div>
			<div class="flex flex-col lg:flex-row m-4">
				<div class="lg:flex lg:items-center lg:w-16">
					<span class="lg:flex lg:justify-center font-bold">제목</span>
				</div>
				<div class="lg:flex-grow">
					<input class="form-row-input w-full" type="text" name="title"
						placeholder="제목을 입력해주세요" autofocus="autofocus" />
				</div>
			</div>
			<div class="flex flex-col lg:flex-row m-4">
				<div class="lg:flex lg:items-center lg:w-16">
					<span class="lg:flex lg:justify-center font-bold">내용</span>
				</div>
				<div class="lg:flex-grow">
					<textarea class="form-row-input w-full" name="body"
						placeholder="내용을 입력해주세요"></textarea>
				</div>
			</div>
			<div class="flex flex-col lg:flex-row m-4">
				<div class="lg:flex lg:items-center lg:w-16">
					<span class="lg:flex lg:justify-center font-bold">첨부파일</span>
				</div>
				<div class="lg:flex-grow">
					<input class="form-row-input w-full" type="file"
						name="file__article__0__common__attachment__1" />
				</div>
			</div>
			<div class="flex justify-center">
				<input type="submit"
					class="bg-blue-500 hover:bg-blue-900 text-white font-bold rounded py-2 px-4 m-2"
					value="입력" /> <input type="button"
					class="bg-red-500 hover:bg-red-900 text-white font-bold rounded py-2 px-4 m-2"
					value="취소" onclick="history.back()" />
			</div>
		</form>
	</div>
</section>
<%@ include file="../part/mainLayoutFooter.jspf"%>