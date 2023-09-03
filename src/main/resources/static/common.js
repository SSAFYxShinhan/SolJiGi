toastr.options = {
    closeButton: true,
    debug: false,
    newestOnTop: true,
    progressBar: true,
    positionClass: "toast-top-right",
    preventDuplicates: false,
    onclick: null,
    showDuration: "300",
    hideDuration: "1000",
    timeOut: "5000",
    extendedTimeOut: "1000",
    showEasing: "swing",
    hideEasing: "linear",
    showMethod: "fadeIn",
    hideMethod: "fadeOut"
};

function checkAnswer(optionIndex, correctAnswer, questionId) {
    if (optionIndex === correctAnswer) {
        toastr.success('정답입니다!');
    } else {
        toastr.error('오답입니다.');
    }
}