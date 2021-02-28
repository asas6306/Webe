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
<section class="section-Login">
	<div class="container mx-auto min-h-screen flex items-center justify-center">
		<div class="w-full">
			<div class="logo-bar flex justify-center mb-4">
				<a href="#"><span><i class="fas fa-seedling">GARDEN</i> </span></a></div>
			<form class="bg-white shadow-md rounded px-8 py-6"
				action="doLogin" method="post"
				onsubmit="LoginForm__checkAndSubmit(this); return false;">
				<div class="flex flex-col md:flex-row">
					<!-- md:flex-row 화면이 넓어지면 row배치 -->
					<div class="w-20 md:flex md:items-center">
						<span>아이디</span>
					</div>
					<div class="md:flex-grow py-2">
						<input
							class="shadow apperance-non border rounded w-full py-2 px-3 text-grey-darker"
							type="text" name="ID" placeholder="아이디를 입력해주세요." maxlength="20"
							autofocus="autofocus" />
					</div>
				</div>
				<div class="flex flex-col md:flex-row">
					<!-- md:flex-row 화면이 넓어지면 row배치 -->
					<div class="w-20 md:flex md:items-center">
						<span>비밀번호</span>
					</div>
					<div class="md:flex-grow py-2">
						<input
							class="shadow apperance-non border border-red rounded w-full py-2 px-3 text-grey-darker"
							type="password" name="PW" placeholder="비밀번호를 입력해주세요."
							maxlength="20" autofocus="autofocus" />
					</div>
				</div>
				<div class="flex">
					<div class="p-3">
						<span></span>
					</div>
					<div class="flex-grow p-3" align="right">
						<input
							class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
							type="submit" value="로그인" />
					</div>
				</div>
			</form>
		</div>
	</div>
</section>

<%@ include file="../part/footer.jspf"%>