$('#exclusionModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);

	var codeTitle = button.data('id');
	var descriptionTitle = button.data('description');

	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');

	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + codeTitle);

	var result = descriptionTitle.substring(0, 1).toUpperCase() + descriptionTitle.substring(1);

	modal.find('.modal-body span').html('Tem certeza que deseja excluir o título <strong>' + result + '</strong>?');
});

$(function() {
	$('[rel="tooltip"]').tooltip();

	$('.js-money').maskMoney({
		decimal: ',',
		thousands: '.',
		allowZero: true
	});
	
	$('.js-refresh-status').on('click', function(event) {
		event.preventDefault();

		var btnReceived = $(event.currentTarget);
		var urlReceived = btnReceived.attr('href');
		
		var response = $.ajax({
			url: urlReceived,
			type: 'PUT'
		});
		
		response.done(function(e) {
			var codeTitle = btnReceived.data('id');
			$('[data-role=' + codeTitle + ']').html('<span class="label label-success">' + e + '</span>');
			btnReceived.hide();
		});
		
		response.fail(function(e){
			console.log(e);
			alert('Erro recebendo cobrança');
		});
	});
});