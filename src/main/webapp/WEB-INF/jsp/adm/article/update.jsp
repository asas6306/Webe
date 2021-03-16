<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../part/mainLayoutHeader.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.example.demo.util.Util"%>>

<c:set var="fileInputMaxCount" value="10" />
<script>
	ArticleUpdate__fileInputMaxCount = parseInt("${fileInputMaxCount}");
	const aid = parseInt("${article.aid}");
</script>

<script>
ArticleUpdate__submited = false;
function ArticleUpdate__checkAndSubmit(form) {
	if ( ArticleUpdate__submited ) {
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
	for ( let inputNo = 1; inputNo <= ArticleUpdate__fileInputMaxCount; inputNo++ ) {
		const input = form["file__article__" + aid + "__common__attachment__" + inputNo];
		
		if (input.value) {
			if (input.files[0].size > maxSize) {
				alert(maxSizeMb + "MB 이하의 파일을 업로드 해주세요.");
				input.focus();
				
				return;
			}
		}
	}
	const startSubmitForm = function(data) {
		if (data && data.body && data.body.genFileIdsStr) {
			form.genFileIdsStr.value = data.body.genFileIdsStr;
		}
		
		for (let inputNo = 1; inputNo <= ArticleUpdate__fileInputMaxCount; inputNo++) {
			const input = form["file__article__" + aid + "__common__attachment__" + inputNo];
			input.value = '';
		}
		
		for(let inputNo = 1; inputNo <= ArticleUpdate__fileInputMaxCount; inputNo++){
			const input = form["deleteFile__article__" + aid + "__common__attachment__" + inputNo];
			
			if ( input ) {
				input.checked = false;
			}
		}
		
		form.submit();
	};
	
	const startUploadFiles = function(onSuccess) {
		var needToUpload = false;
		for ( let inputNo = 1; inputNo <= ArticleUpdate__fileInputMaxCount; inputNo++ ) {
			const input = form["file__article__" + aid + "__common__attachment__" + inputNo];
			if ( input.value.length > 0 ) {
				needToUpload = true;
				break;
			}
		}
		
		if ( needToUpload == false ) {
			for ( let inputNo = 1; inputNo <= ArticleUpdate__fileInputMaxCount; inputNo++ ) {
				const input = form["deleteFile__article__" + aid + "__common__attachment__" + inputNo];
				if ( input && input.checked ) {
					needToUpload = true;
					break;
				}
			}
		}
		
		if (needToUpload == false) {
			onSuccess();
			return;
		}
		
		var fileUploadFormData = new FormData(form);
		$.ajax({
			url : '/common/genFile/doUpload',
			data : fileUploadFormData,
			processData : false,
			contentType : false,
			dataType : "json",
			type : 'POST',
			success : onSuccess
		});
	}
	ArticleUpdate__submited = true;
	startUploadFiles(startSubmitForm);
}
</script>


<section class="section-1">
	<div class="bg-white shadow-lg mt-4 container mx-auto rounded p-4">
		<div class="mx-16 my-8">
			<div class="flex justify-center text-4xl font-extrabold">게시물 수정</div>

			<form onsubmit="ArticleUpdate__checkAndSubmit(this); return false;"
				action="doUpdate" method="post" enctype="multipart/form-data">
				<input type="hidden" name="genFileIdsStr" value="" /> <input
					type="hidden" name="aid" value="${article.aid}" />
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
						$('.section-1 .select-board').val(${article.boardCode});

					</script>
					</div>
				</div>
				<div class="flex flex-col lg:flex-row m-4">
					<div class="lg:flex lg:items-center lg:w-16">
						<span class="lg:flex lg:justify-center font-bold">제목</span>
					</div>
					<div class="lg:flex-grow">
						<input class="form-row-input w-full" type="text" name="title"
							value="${article.title}" placeholder="제목을 입력해주세요"
							autofocus="autofocus" />
					</div>
				</div>
				<div class="flex flex-col lg:flex-row m-4">
					<div class="lg:flex lg:items-center lg:w-16">
						<span class="lg:flex lg:justify-center font-bold">내용</span>
					</div>
					<div class="lg:flex-grow">
						<textarea class="form-row-input w-full" name="body"
							placeholder="내용을 입력해주세요">${article.body}</textarea>
					</div>
				</div>
				<c:forEach begin="1" end="${fileInputMaxCount}" var="inputNo">
					<c:set var="fileNo" value="${String.valueOf(inputNo)}"></c:set>
					<c:set var="file"
						value="${article.extra.file__common__attachment[fileNo]}"></c:set>
					<div class="flex flex-col lg:flex-row m-4">
						<div class="lg:flex lg:items-center lg:w-20">
							<span class="lg:flex lg:justify-center font-bold">첨부파일${inputNo}</span>
						</div>
						<div class="input-file-wrap lg:flex-grow">
							<c:if test="${file != null && file.fileExtTypeCode == 'img'}">
								<div class="img-box img-box-auto">
									<a href="${file.forPrintUrl}" target="_blank" title="자세히 보기">
										<img class="max-w-sm" src="${file.forPrintUrl}" />
									</a>
								</div>
								<div>
									<a class="hover:underline" href="${file.downloadUrl}"
										target="_blank">${file.originFileName}</a>
									(${Util.numberFormat(file.fileSize)} Byte )

								</div>
								<div>
									<label> <input type="checkbox"
										onclick="$(this).closest('.input-file-wrap').find(' > input[type=file]').val('')"
										name="deleteFile__article__${article.aid}__common__attachment__${fileNo}"
										value="Y" /> <span>삭제</span>
									</label>
								</div>
							</c:if>
							<input class="form-row-input w-full" type="file"
								name="file__article__${article.aid}__common__attachment__${inputNo}" />
						</div>
					</div>
				</c:forEach>
				<div class="flex justify-center">
					<input type="submit"
						class="bg-blue-500 hover:bg-blue-900 text-white font-bold rounded py-2 px-4 m-2"
						value="수정" /> <input type="button"
						class="bg-red-500 hover:bg-red-900 text-white font-bold rounded py-2 px-4 m-2"
						value="취소" onclick="history.back()" />
				</div>
			</form>
		</div>
	</div>
</section>
<%@ include file="../part/mainLayoutFooter.jspf"%>