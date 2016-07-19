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
