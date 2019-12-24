// window.onload=function() {
//     // 필수 입력 항목 박스 커스터마이징
//     $(document).ready(function () {
//         var submitButton = document.getElementById("submit_button");
//
//         submitButton.addEventListener("click", function (e) {
//             var required = document.querySelectorAll("input[required]");
//
//             required.forEach(function (element) {
//                 if (element.value.trim() == "") {
//                     element.style.backgroundColor = "red";
//                 } else {
//                     element.style.backgroundColor = "white";
//                 }
//             });
//         });
//     });
// };

$(document).ready(function () {
    new FroalaEditor('textarea#froala-editor');
});