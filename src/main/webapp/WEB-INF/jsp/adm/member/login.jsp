<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../part/header.jspf"%>

<script>
	const LoginForm__checkAndSubmitDone = false;
	function LoginForm__checkAndSubmit(form) {
		if (LoginForm__checkAndSubmitDone) {
			return;
		}

		form.ID.value = form.ID.value.trim();
		form.PW.value = form.PW.value.trim();

		if (form.ID.value.length == 0) {
			alert('아이디를 입력해주세요.');
			form.ID.focus();

			return;
		}

		if (form.PW.value.length == 0) {
			alert('비밀번호를 입력해주세요.');
			form.PW.focus();

			return;
		}

		form.submit();
		LoginForm__checkAndSubmitDone = true;
	}
</script>
<section class="section-Login h-screen">
	<div class="container mx-auto h-full flex items-center justify-center">
		<form class="bg-white w-full shadow-md rounded px-8 pt-6 pb-8 mb-4" action="doLogin" method="post"
			onsubmit="LoginForm__checkAndSubmit(this); return false;">
			<div>
				<div class="pl-5 pt-3">
					<span>아이디</span>
				</div>
				<div class="flex-grow px-3 py-2">
					<input class="shadow apperance-non border rounded w-full py-2 px-3 text-grey-darker" type="text" name="ID"
						placeholder="아이디를 입력해주세요." maxlength="20" autofocus="autofocus" />
				</div>
			</div>
			<!-- 
			<div class="flex">
				<div class="p-6 w-36" align="center">
					<span>비밀번호</span>
				</div>
				<div class="flex-grow p-3">
					<input class="shadow apperance-non border border-red rounded w-full py-2 px-3 text-grey-darker" type="password" name="PW"
						placeholder="비밀번호를 입력해주세요." maxlength="20" autofocus="autofocus" />
				</div>
			</div> 
			-->
			<div>
				<div class="pl-5 pt-3">
					<span>비밀번호</span>
				</div>
				<div class="flex-grow px-3 py-2">
					<input class="shadow apperance-non border border-red rounded w-full py-2 px-3 text-grey-darker" type="password" name="PW"
						placeholder="비밀번호를 입력해주세요." maxlength="20" autofocus="autofocus" />
				</div>
			</div>
			<div class="flex">
				<div class="p-4">
					<span></span>
				</div>
				<div class="flex-grow p-4" align="right">  
					<input class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded" type="submit" value="로그인" />
				</div>
			</div>
		</form>
	</div>
</section>

<%@ include file="../part/footer.jspf"%>