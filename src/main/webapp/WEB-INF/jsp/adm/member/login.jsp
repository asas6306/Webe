<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.0.3/tailwind.min.css" />
</head>
<body>
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
		<div class="container mx-auto">
			<form action="doLogin" method="post"
				onsubmit="LoginForm__checkAndSubmit(this); return false;">
				<div class="flex">
					<div class="p-4 w-36" align="center">
						<span>아이디</span>
					</div>
					<div class="flex-grow p-4">
						<input class="w-full" type="text" name="ID"
							placeholder="아이디를 입력해주세요." maxlength="20" autofocus="autofocus" />
					</div>
				</div>
				<div class="flex">
					<div class="p-4 w-36" align="center">
						<span>비밀번호</span>
					</div>
					<div class="flex-grow p-4">
						<input class="w-full" type="password" name="PW"
							placeholder="비밀번호를 입력해주세요." maxlength="20" autofocus="autofocus" />
					</div>
				</div>
				<div class="flex">
					<div class="p-4">
						<span></span>
					</div>
					<div class="flex-grow p-4">
						<input class="w-full" type="submit" value="로그인" />
					</div>
				</div>
			</form>
		</div>
	</section>
</body>
</html>