function quickMenu() {
    $(this).toggleClass('open');
    $('.control-center').toggleClass('open');
}

function goTop() {
    $('html, body').animate({scrollTop: 0}, 'slow');
}

function goBottom() {
    $('html, body').animate({scrollTop: $(document).height()}, 'slow');
}