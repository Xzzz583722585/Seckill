var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeader = $("meta[name='_csrf_header']").attr("content");

$(function () {
    window.sessionStorage.setItem("csrfToken", csrfToken)
    window.sessionStorage.setItem("csrfHeader", csrfHeader)

    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(csrfHeader, csrfToken);
    });
});