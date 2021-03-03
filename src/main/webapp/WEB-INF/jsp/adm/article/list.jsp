<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../part/mainLayoutHeader.jspf"%>

<section class="flex justify-center">
	<div
		class="bg-white shadow-lg mt-4 container mx-auto flex justify-center rounded p-4">
		<div>
			<c:forEach items='${articles}' var='article'>
				<div>${article.aid} / ${article.nickname} / ${article.title}</div>
			</c:forEach>
		</div>
	</div>
</section>

<%@ include file="../part/mainLayoutFooter.jspf"%>