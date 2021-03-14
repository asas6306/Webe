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
						<option value="0">전체게시판</option>
						<option value="1">공지사항</option>
						<option value="2">자유게시판</option>
					</select>
					<script>
						$('.section-1 .select-board').val(${boardCode});

						$('.section-1 .select-board').change(function() {
							location.href = '?boardCode=' + this.value;
						});
					</script>
				</div>

				<div class="flex-grow"></div>
				<c:if test="${boardCode != 0}">
					<a href="/adm/article/add?boardCode=${boardCode}"
						class="bg-blue-500 hover:bg-blue-900 text-white font-bold rounded py-2 px-4">글쓰기</a>
				</c:if>
			</div>
			<div>
				<c:forEach items='${articles}' var='article'>
					<c:set var="detailUrl" value="detail?aid=${article.aid}" />
					<div class="flex items-center mt-10">
						<a href="${detailUrl}" class="font-bold text-gray-600 mr-2">${article.aid}</a>
						<span class="font-light text-gray-600">${article.regDate}</span>
						<div class="flex-grow"></div>
						<a href="list?boardCode=${article.boardCode}"
							class="px-2 py-1 bg-gray-600 text-gray-100 font-bold rounded hover:bg-gray-500">${article.boardName}</a>
					</div>
					<div class="${detailUrl}">
						<a href="${detailUrl}"
							class="text-2xl text-gray-700 font-bold hover:underline">${article.title}</a>
						<a class="text-gray-600 block" href="${detailUrl}">${article.body}</a>
						<c:if test="${article.extra__thumbImg != null}">
							<a class="block" href="${detailUrl}">
								<img class="max-w-sm" src="${article.extra__thumbImg}" />
							</a>
						</c:if>
					</div>
					<div class="flex items-center mt-4">
						<a href="${detailUrl}" class="text-gray-500 hover:underline">자세히
							보기</a> <a href="update?aid=${article.aid}"
							class="mx-2 text-blue-500 hover:underline">수정</a> <a
							href="delete?aid=${article.aid}"
							class="text-red-500 hover:underline">삭제</a>
						<div class="flex-grow"></div>
						<div>
							<a href="" class="flex items-center"> <img
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