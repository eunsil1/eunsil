const tabLinks = document.querySelectorAll('.tab-link');
const tabContents = document.querySelectorAll('.tab-content');

tabLinks.forEach(function(button) {
    button.addEventListener('click', function() {
        const targetId = button.getAttribute("data-tab");

        // 모든 버튼을 비활성화
        tabLinks.forEach(btn => btn.classList.remove('active'));
        // 내용을 숨기기
        tabContents.forEach(content => content.classList.remove('active'));

        // 현재 클릭한 버튼(색상변경) / 내용 활성화
        button.classList.add("active");
        document.getElementById(targetId).classList.add("active");
    });
});