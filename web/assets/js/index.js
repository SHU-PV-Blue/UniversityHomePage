/**
 * Created by wolfogre on 16-7-19.
 */
$(document).ready(function() {
    $('#example').DataTable();
    $('#exampleModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        var name = button.data('name'); // Extract info from data-* attributes
        var image =  "assets/images/" + button.data('image');
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this);
        modal.find('.modal-title').text(name);
        modal.find('#page-img').attr("src","");
        modal.find('#page-img').attr("src",image);
        if(button.data('mobile')){
            modal.find('.modal-dialog').removeClass('modal-lg');
            modal.find('.modal-dialog').addClass('modal-sm');
        } else {
            modal.find('.modal-dialog').removeClass('modal-sm');
            modal.find('.modal-dialog').addClass('modal-lg');
        }
        Countly.q.push(['add_event',{
            key:"visitUniversity",
            "segmentation": {
                "name": name
            }
        }]);
    });

    $('.content').bind('input propertychange',function (event) {
        $(this).parent().find('.content-update').removeAttr('disabled');
    });

    $('.content-update').bind('click',function (event) {
        var thisButtun = $(this);
        thisButtun.text("稍等");
        var aimId = thisButtun.data("id");
        var aimValue = $(this).parent().find('.content').val();
        $.ajax({
            type: "GET",
            url: "content/set",
            data: {id:aimId, value:aimValue},
            dataType: "json",
            success: function(data){
                if(!data.success){
                    thisButtun.text("更新");
                    alert("错误:" + data.reason);
                    return;
                }
                thisButtun.text("更新");
                thisButtun.attr('disabled', 'disabled');
            }
        });
    });

} );

//some default pre init
var Countly = Countly || {};
Countly.q = Countly.q || [];

//provide countly initialization parameters
Countly.app_key = '3cd6ece101116249bf471b357cb0fa70692ef6e9';
Countly.url = 'http://countly.wolfogre.com'; //or none for default countly cloud

Countly.q.push(['track_sessions']);
Countly.q.push(['track_pageview']);
Countly.q.push(['track_clicks']);
Countly.q.push(['track_errors']);
Countly.q.push(['track_links']);
Countly.q.push(['track_forms']);

//load countly script asynchronously
(function() {
    var cly = document.createElement('script'); cly.type = 'text/javascript';
    cly.async = true;
    //enter url of script here
    cly.src = 'https://cdnjs.cloudflare.com/ajax/libs/countly-sdk-web/16.2.0/countly.min.js';
    cly.onload = function(){Countly.init()};
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(cly, s);
})();