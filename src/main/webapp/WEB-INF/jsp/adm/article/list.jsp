<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../part/mainLayoutHeader.jspf"%>
<section class="section-1">
	<div class="bg-white shadow-lg mt-4 container mx-auto rounded p-4">
		<div class="mx-16 my-8">
			<div class="flex justify-center">
				<div class="text-4xl font-extrabold">게시판</div>
			</div>
			<div class="flex">
				<div>
					<select class="py-2 select-board">
						<option value="0" selected="selected">전체게시판</option>
						<option value="1">공지사항</option>
						<option value="2">자유게시판</option>
					</select>
					<script>
						$('.section-1 .select-board').val(param.boardCode);

						$('.section-1 .select-board').change(function() {
							location.href = '?boardCode=' + this.value;
						});
					</script>
				</div>

				<div class="flex-grow"></div>

				<a href="/adm/article/add?boardCode=${param.boardCode}"
					class="bg-blue-500 hover:bg-blue-900 text-white font-bold rounded py-2 px-4">글쓰기</a>
			</div>
			<div>
				<c:forEach items='${articles}' var='article'>
					<div>
						<div class="flex justify-between items-center mt-10">
							<span class="font-light text-gray-600">${article.regDate}</span>
							<a href="list?boardId=${article.boardCode}"
								class="px-2 py-1 bg-gray-600 text-gray-100 font-bold rounded hover:bg-gray-500">${article.boardName}</a>
						</div>
						<div class="mt-2">
							<a href="detail?id=${article.aid}"
								class="text-2xl text-gray-700 font-bold hover:underline">${article.title}</a>
							<p class="mt-2 text-gray-600">${article.body}</p>
						</div>
						<div class="flex justify-between items-center mt-4">
							<a href="detail?id=${article.aid}"
								class="text-blue-500 hover:underline">자세히 보기</a>
							<div>
								<a href="detail?id=${article.aid}" class="flex items-center">
									<img
									src="https://images.unsplash.com/photo-1492562080023-ab3db95bfbce?ixlib=rb-1.2.1&amp;ixid=eyJhcHBfaWQiOjEyMDd9&amp;auto=format&amp;fit=crop&amp;w=731&amp;q=80"
									alt="avatar" class="mx-4 w-10 h-10 object-cover rounded-full">
									<h1 class="text-gray-700 font-bold hover:underline">${article.nickname}</h1>
								</a>
							</div>
						</div>
				</c:forEach>
			</div>
		</div>
	</div>
</section>

<%@ include file="../part/mainLayoutFooter.jspf"%>