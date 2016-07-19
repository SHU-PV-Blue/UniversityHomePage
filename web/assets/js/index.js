/**
 * Created by wolfogre on 16-7-19.
 */
$(document).ready(function() {
    $('#example').DataTable();
    $('#exampleModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var name = button.data('name') // Extract info from data-* attributes
        var image =  "assets/images/" + button.data('image')
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this)
        modal.find('.modal-title').text(name)
        var test = modal.find('#page-img')
        test.attr("src",image)
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