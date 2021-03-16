<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../part/header.jspf"%>

<script>
	const SignupForm__checkAndSubmitDone = false;
	function SignupForm__checkAndSubmit(form) {
		if (SignupForm__checkAndSubmitDone) {
			return;
		}

		form.ID.value = form.ID.value.trim();
		form.PW1.value = form.PW1.value.trim();
		form.PW2.value = form.PW2.value.trim();
		form.nickname.value = form.nickname.value.trim();
		form.phoneNo.value = form.phoneNo.value.trim();

		if (form.ID.value.length == 0) {
			alert('아이디를 입력해주세요.');
			form.ID.focus();

			return;
		}

		if (form.PW1.value.length == 0) {
			alert('비밀번호를 입력해주세요.');
			form.PW1.focus();

			return;
		}

		if (form.PW2.value.length == 0) {
			alert('비밀번호를 입력해주세요.');
			form.PW2.focus();

			return;
		}
		
		if (form.PW1.value != form.PW2.value) {
			alert('비밀번호가 일치하지 않습니다..');
			form.PW1.focus();

			return;
		}

		if (form.nickname.value.length == 0) {
			alert('닉네임을 입력해주세요.');
			form.nickname.focus();

			return;
		}

		if (form.email1.value.length == 0) {
			alert('이메일을 입력해주세요.');
			form.email1.focus();

			return;
		}

		if (form.phoneNo.value.length == 0) {
			alert('휴대폰 번호를 입력해주세요.');
			form.phoneNo.focus();

			return;
		}

		form.submit();
		SignupForm__checkAndSubmitDone = true;
	}
</script>
<section class="section-Login">
	<div
		class="container mx-auto min-h-screen flex items-center justify-center">
		<div class="w-full">
			<div class="logo-bar flex justify-center mb-4">
				<a href="#"> <span> <i class="fas fa-seedling">GARDEN<i
							class="text-lg font-bold">ADMIN</i></i>
				</span>
				</a>
			</div>
			<form class="bg-white shadow-md rounded px-8 py-6" action="doSignup"
				method="post"
				onsubmit="SignupForm__checkAndSubmit(this); return false;">
				<div class="flex">
					<!--  화면이 넓어지면 row배치 -->
					<div class="w-20 flex items-center">
						<span>아이디</span>
					</div>
					<div class="flex-grow py-2">
						<input
							class="shadow apperance-non border rounded w-full py-2 px-3 text-grey-darker"
							type="text" name="ID" placeholder="아이디를 입력해주세요." maxlength="20"
							autofocus="autofocus" />
					</div>
					<div class="flex items-center pl-4">
						<input
							class="bg-blue-500 hover:bg-blue-700 text-white py-2 px-4 font-bold rounded"
							type="button" value="중복확인" onclick="alert('음... 중복검사 어떻게 하징ㅎㅎ');" />
					</div>
				</div>
				<div class="flex flex-row">
					<div class="w-20 flex items-center">
						<span>비밀번호</span>
					</div>
					<div class="flex-grow py-2">
						<input
							class="shadow apperance-non border border-red rounded w-full py-2 px-3 text-grey-darker"
							type="password" name="PW1" placeholder="비밀번호를 입력해주세요."
							maxlength="20" />
					</div>
				</div>
				<div class="flex flex-row">
					<div class="w-20 flex items-center">
						<span>비밀번호 확인</span>
					</div>
					<div class="flex-grow py-2">
						<input
							class="shadow apperance-non border border-red rounded w-full py-2 px-3 text-grey-darker"
							type="password" name="PW2" placeholder="비밀번호를 입력해주세요."
							maxlength="20" />
					</div>
				</div>
				<div class="flex flex-row">
					<div class="w-20 flex items-center">
						<span>닉네임</span>
					</div>
					<div class="flex-grow py-2">
						<input
							class="shadow apperance-non border border-red rounded w-full py-2 px-3 text-grey-darker"
							type="text" name="nickname" placeholder="닉네임을 입력해주세요."
							maxlength="20" />
					</div>
					<div class="flex items-center pl-4">
						<input
							class="bg-blue-500 hover:bg-blue-700 text-white py-2 px-4 font-bold rounded"
							type="button" value="중복확인" onclick="alert('음... 중복검사 어떻게 하징ㅎㅎ');" />
					</div>
				</div>
				<div class="flex flex-row">
					<div class="w-20 flex items-center">
						<span>이메일</span>
					</div>
					<div class="flex-grow py-2">
						<input
							class="shadow apperance-non border border-red rounded w-full py-2 px-3 text-grey-darker"
							type="text" name="email1" maxlength="20" />
					</div>
					<div class="flex items-center p-2">
						<span class="text-2xl">@</span>
					</div>
					<div class="flex items-center flex-grow py-2">
						<select
							class="shadow apperance-non border border-red rounded w-full py-2 px-3 text-grey-darker"
							name="eamil2">
							<option value="naver.com">naver.com</option>
							<option value="google.com">google.com</option>
							<option value="kakao.com">직접입력</option>
						</select>
					</div>
				</div>
				<div class="flex flex-row">
					<div class="w-20 flex items-center">
						<span>연락처</span>
					</div>
					<div class="flex-grow py-2">
						<input
							class="shadow apperance-non border border-red rounded w-full py-2 px-3 text-grey-darker"
							type="text" name="phoneNo" maxlength="20" />
					</div>
					<div class="flex items-center pl-4">
						<input
							class="bg-blue-500 hover:bg-blue-700 text-white py-2 px-4 font-bold rounded"
							type="button" value="인증" onclick="alert('음... 인증 어떻게 하징ㅎㅎ');" />
					</div>
				</div>
				<div class="flex">
					<div class="p-3">
						<span></span>
					</div>
					<div class="flex-grow p-3" align="center">
						<input
							class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 mr-1 rounded"
							type="submit" value="확인" /> <input
							class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
							type="button" value="취소" onclick="history.back()" />
					</div>
				</div>
			</form>
		</div>
	</div>
</section>

<%@ include file="../part/footer.jspf"%>