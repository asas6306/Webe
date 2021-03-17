<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../part/mainLayoutHeader.jspf"%>
<section class="section-1">
	<div class="bg-white shadow-lg mt-4 container mx-auto rounded p-4">
		<div class="mx-16 my-8">
			<div class="flex justify-center">
				<div class="text-4xl font-extrabold">회원목록</div>
			</div>
			<div class="flex">
				<div>
					<select class="py-2 select-board">
						<option value="0">전체회원</option>
						<option value="3">일반회원</option>
						<option value="7">관리자</option>
					</select>
					<script>
						$('.section-1 .select-board').val(${authLevel});

						$('.section-1 .select-board').change(function() {
							location.href = '?authLevel=' + this.value;
						});
					</script>
				</div>

				<div class="flex-grow"></div>
			</div>
			<div>
				<c:forEach items='${members}' var='member'>
					<c:set var="detailUrl" value="detail?uid=${member.uid}" />
					<div class="flex items-center mt-10">
						<a href="#" class="font-bold text-gray-600 mr-2">${member.uid}</a>
						<span class="font-light text-gray-600">${member.regDate}</span>
						<div class="flex-grow"></div>
						<a href="list?boardCode=${member.authLevel}"
							class="px-2 py-1 bg-gray-600 text-gray-100 font-bold rounded hover:bg-gray-500">${member.authLevel}</a>
					</div>
					<div class="${#}">
						<a href="${#}"
							class="text-2xl text-gray-700 font-bold hover:underline">${member.ID}</a>
						<a class="text-gray-600 block" href="${#}">${member.nickname}</a>
					</div>
					<div class="flex items-center mt-4">
						<a href="update?uid=${member.uid}"
							class="mx-2 text-blue-500 hover:underline">수정</a> <a
							href="delete?aid=${member.uid}"
							class="text-red-500 hover:underline">삭제</a>
						<div class="flex-grow"></div>
						<div>
							<a href="" class="flex items-center"> <img
								src="https://images.unsplash.com/photo-1492562080023-ab3db95bfbce?ixlib=rb-1.2.1&amp;ixid=eyJhcHBfaWQiOjEyMDd9&amp;auto=format&amp;fit=crop&amp;w=731&amp;q=80"
								alt="avatar" class="mx-4 w-10 h-10 object-cover rounded-full">
								<h1 class="text-gray-700 font-bold hover:underline">${member.nickname}</h1>
							</a>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</section>

<%@ include file="../part/mainLayoutFooter.jspf"%>