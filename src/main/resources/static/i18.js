//$(document).ready(function() {
//    $("#locales").change(function () {
//        var selectedOption = $('#locales').val();
//        if (selectedOption != ''){
//            window.location.replace('locale?lang=' + selectedOption);
//        }
//    });
//});


$(document).ready(function() {
    $("#locales").change(function () {
        var selectedOption = $(this).val();  // Use 'this' instead of re-querying
        if (selectedOption !== '') {
            window.location.replace('locale?lang=' + selectedOption);
        }
    });
});
