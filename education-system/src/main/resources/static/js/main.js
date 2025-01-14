/* 채팅하기 버튼*/
$('.in').click(function(event) {

	let name = $(event.target).data('name');

	if (name == null || name == '') {
		name = $('#name').val();
	} else {
		$('#name').val(name);
	}

	if (name == null || name == '') {
		$('#name').focus();
		return;
	}

	const child = window.open('/chat', 'chat', 'width=406 height=520');

	$('#name').prop('readOnly', true);
	$('.in').prop('disabled', true);
	$('.in').css('opacity', .5);


	// 시간 예측 안하고 로딩이 끝나면 넣어짐
	child.addEventListener('load', () => {
		child.connect($('#name').val());
	});




});