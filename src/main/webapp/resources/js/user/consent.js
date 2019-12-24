function joinContinue() {
    var termOk = $('#c_joinConsentCheck1'); // 이용약관 동의 라디오 버튼
    var privacyOk = $('#c_joinConsentCheck3'); // 개인정보 동의 라디오 버튼

    if (! termOk.is(":checked")) {
        alert('계정 등록 이용약관을 확인해주세요.');
        termOk.focus();
    } else if (! privacyOk.is(":checked")) {
        alert('개인정보 수집 및 이용을 확인해주세요.');
        privacyOk.focus();
    } else {
        window.location.href = '/user/join';
    }
}