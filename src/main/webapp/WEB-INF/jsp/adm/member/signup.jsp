<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../part/header.jspf"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.17.21/lodash.min.js"></script>

<script>
	const SignupForm__checkAndSubmitDone = false;

	let SignupForm__validID = '';
	//로그인 아이디 중복체크 함수
	function SignupForm__checkIDDup() {
		const form = $('.formLogin').get(0);

		form.ID.value = form.ID.value.trim();

		if (form.ID.value.length == 0) {
			return;
		}

		$.get('getLoginIdDup', {
			ID : form.ID.value
		}, function(data) {
			let colorClass = 'text-green-500';

			if (data.fail) {
				colorClass = 'text-red-500';
			}

			$('.IDInputMsg').html(
					"<span class='" + colorClass + "'>" + data.msg);

			if (data.fail) {
				form.ID.focus();
			} else {
				SignupForm__validID = data.body.ID;
			}
		}, 'json');
	}

	function SignupForm__checkAndSubmit(form) {
		if (SignupForm__checkAndSubmitDone) {
			return;
		}

		form.ID.value = form.ID.value.trim();

		if (form.ID.value.length == 0) {
			alert('아이디를 입력해주세요.');
			form.ID.focus();

			return;
		}

		if (form.ID.value != SignupForm__validID) {
			alert('아이디 중복체크를 해주세요.');
			form.ID.focus();

			return;
		}

		form.PW.value = form.PW.value.trim();

		if (form.PW.value.length == 0) {
			alert('비밀번호를 입력해주세요.');
			form.PW.focus();

			return;
		}

		form.PWCheck.value = form.PWCheck.value.trim();

		if (form.PWCheck.value.length == 0) {
			alert('비밀번호를 입력해주세요.');
			form.PWCheck.focus();

			return;
		}

		if (form.PW.value != form.PWCheck.value) {
			alert('비밀번호가 일치하지 않습니다..');
			form.PW.focus();

			return;
		}

		form.nickname.value = form.nickname.value.trim();

		if (form.nickname.value.length == 0) {
			alert('닉네임을 입력해주세요.');
			form.nickname.focus();

			return;
		}

		form.email1.value = form.email1.value.trim();

		if (form.email1.value.length == 0) {
			alert('이메일을 입력해주세요.');
			form.email1.focus();

			return;
		}

		form.phoneNo.value = form.phoneNo.value.trim();

		if (form.phoneNo.value.length == 0) {
			alert('휴대폰 번호를 입력해주세요.');
			form.phoneNo.focus();

			return;
		}

		const submitForm = function(data) {
			if (data) {
				form.genFileIdsStr.value = data.body.genFileIdsStr;
			}

			form.submit();
			SignupForm__checkAndSubmitDone = true;
		}

		function startUpload(onSuccess) {
			if (!form.file__member__0__common__attachment__1.value) {
				onSuccess("");
				return;
			}

			const formData = new FormData(form);

			$.ajax({
				url : '/common/genFile/doUpload',
				data : formData,
				processData : false,
				contentType : false,
				dataType : "json",
				type : 'POST',
				success : onSuccess
			});

			// 파일업로드 > 기다림 > 응답 > onSuccess
		}
		
		startUpload(submitForm);
	}

	// 중복체크
	$(function() {
		$('.inputLoginId').change(function() {
			SignupForm__checkIDDup();
		});
		$('.inputLoginId').keyup(_.debounce(SignupForm__checkIDDup, 1000));

	});
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
			<form class="formLogin bg-white shadow-md rounded px-8 py-6"
				action="doSignup" method="post"
				onsubmit="SignupForm__checkAndSubmit(this); return false;">
				<input type="hidden" name="genFileIdsStr">
				<div class="flex">
					<div class="w-24 flex items-center">
						<span>아이디</span>
					</div>
					<div class="flex-grow py-2">
						<input
							class="inputLoginId shadow apperance-non border rounded w-full py-2 px-3 text-grey-darker"
							type="text" name="ID" placeholder="아이디를 입력해주세요." maxlength="20"
							autofocus="autofocus" />
					</div>
				</div>
				<div class="IDInputMsg pl-20"></div>
				<div class="flex flex-row">
					<div class="w-24 flex items-center">
						<span>비밀번호</span>
					</div>
					<div class="flex-grow py-2">
						<input
							class="shadow apperance-non border border-red rounded w-full py-2 px-3 text-grey-darker"
							type="password" name="PW" placeholder="비밀번호를 입력해주세요."
							maxlength="20" />
					</div>
				</div>
				<div class="flex flex-row">
					<div class="w-24 flex items-center">
						<span>비밀번호 확인</span>
					</div>
					<div class="flex-grow py-2">
						<input
							class="shadow apperance-non border border-red rounded w-full py-2 px-3 text-grey-darker"
							type="password" name="PWCheck" placeholder="비밀번호를 입력해주세요."
							maxlength="20" />
					</div>
				</div>
				<div class="flex flex-row">
					<div class="w-24 flex items-center">
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
							type="button" value="중복확인" onclick="" />
					</div>
				</div>
				<div class="flex flex-row">
					<div class="w-24 flex items-center">
						<span>프로필이미지</span>
					</div>
					<div class="flex-grow py-2">
						<input
							class="shadow apperance-non border border-red rounded w-full py-2 px-3 text-grey-darker"
							type="file" name="file__member__0__common__attachment__1"
							accept="image/gif, image/jpeg, image/png" maxlength="20" />
					</div>
				</div>
				<div class="flex flex-row">
					<div class="w-24 flex items-center">
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
							name="email2">
							<option value="naver.com">naver.com</option>
							<option value="google.com">google.com</option>
							<option value="kakao.com">직접입력</option>
						</select>
					</div>
				</div>
				<div class="flex flex-row">
					<div class="w-24 flex items-center">
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