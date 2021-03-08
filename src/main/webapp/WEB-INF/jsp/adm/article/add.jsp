<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../part/mainLayoutHeader.jspf"%>
<section class="section-1">
	<div class="bg-white shadow-lg mt-4 container mx-auto rounded p-4">
		<div class="flex justify-center">
			<div class="text-4xl font-extrabold">게시물 작성</div>
		</div>
		<form action="doAdd" method="post">
			<input type="hidden" name="boardCode" value="${boardCode}">
			<div class="flex flex-col lg:flex-row m-4">
				<div class="lg:flex lg:items-center lg:w-16">
					<span class="lg:flex lg:justify-center font-bold">제목</span>
				</div>
				<div class="lg:flex-grow bg-black">
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
			<div class="flex justify-center">
					<input type="submit" class="bg-blue-500 hover:bg-blue-900 text-white font-bold rounded py-2 px-4 m-2" value="입력" />
					<input type="button" class="bg-red-500 hover:bg-red-900 text-white font-bold rounded py-2 px-4 m-2" value="취소"
						onclick="history.back()" />
			</div>
		</form>
	</div>
</section>
<%@ include file="../part/mainLayoutFooter.jspf"%>