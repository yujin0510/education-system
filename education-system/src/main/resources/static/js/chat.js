let name; // 대화명
	
const url = 'ws://localhost:8090/chatService';
let ws;

// 대화명 설정 + 서버 연결
function connect(userName){
	name = userName;
	//alert(name);
	$('#header small').text(name);
	
	// 연결하기 + 소켓 생성
	ws = new WebSocket(url);
	log('서버에게 연결을 시도합니다.');
	
	ws.onopen = evt =>{
		log('서버와 연결되었습니다.');
		
		// 서버와 접속 확인 > 접속한 유저명을 서버에게 전달
		//ws.send('강아지'); //저 강아지에요...
		

		const message = {
				code : 1,
				sender : name,
				content : '',
				creationDate : dayjs().format('YYYY-MM-DD HH:mm:ss') // 2024-10-24 12:28:46
		};
		console.log("여기 확인하기: " + JSON.stringify(message));
		// 객체 > JSON(문자열) > 객체를 JSON문자열로 변경할거임		
		ws.send(JSON.stringify(message));
		
	
	};
	ws.onmessage = evt =>{
		//{"code":1,"sender":"강아지","content":"","regdate":"2024-10-24 12:32:41"}
		
		log('메시지를 수신했습니다.');
		// "Server received: " 부분을 제거하고 JSON 파싱
		// const jsonData = evt.data.replace(/^Server received: /, '');
		const message = JSON.parse(evt.data);

		//document.title = message.sender;
		
		if(message.code == '1'){
			print('', `[${message.sender}]님이 들어왔습니다.`, 'other', 'state', message.creationDate);
			
		}else if(message.code == '2'){
			print('', `[${message.sender}]님이 나갔습니다.`, 'other', 'state', message.creationDate);
			
		}else if(message.code == '3'){
			print(message.sender, message.content, 'other', 'msg', message.creationDate);
			
		}else if(message.code == '4'){
			printEmoticon(message.sender, message.content, 'other', 'msg', message.creationDate);
			
		}
		
		``
	};
	ws.onclose = evt =>{
		log('서버와 연결이 종료되었습니다.');
	
		
		
	};
	ws.onerror = evt =>{
		log('에러 발생' + evt);
	};
	
}

function log(msg){
	console.log(`[${new Date().toLocaleTimeString()}] ${msg}`);
}

// 창을 닫을 때 부모 창 설정 변경
window.onunload = () =>{
	//opener.document.title = 'aaa';
	
	$(opener.document).find('#name').prop('readOnly', false);
	$(opener.document).find('#name').val('');
	$(opener.document).find('#name').focus();
	$(opener.document).find('.in').prop('disabled', false);
	$(opener.document).find('.in').css('opacity', 1);
	
	// 서버에게 종료한다고 알리기
	disconnect();
	
	
	
};

function disconnect(){
	
	// 소켓 연결 종료
	const message = {
			code : 2,
			sender : name,
			content : '',
			creationDate : dayjs().format('YYYY-MM-DD HH:mm:ss') // 2024-10-24 12:28:46
	};
	console.log("여기 확인하기: " + JSON.stringify(message));
	ws.send(JSON.stringify(message));
	
	ws.close();
	
}



function print(name, msg, side, state, time){
	let temp = `
		<div class="item ${state} ${side}">
			<div>
				<div>${name}</div>
				<div>${msg}</div>					
			</div>
			<div>${time}</div>
		</div>
	
	`;
	
	$('#list').append(temp);
	scrollList(); // 스크롤 내리기
}


$('#msg').keydown(evt =>{
	if(evt.keyCode == 13){
		// 대화 내용을 서버로 전송하기
		const message = {
				code : 3,
				sender : name,
				content : $(evt.target).val(),
				creationDate : dayjs().format('YYYY-MM-DD HH:mm:ss') // 2024-10-24 12:28:46
		};
		
		
		if($(evt.target).val().startsWith('/')){
			message.code = 4; // 이모티콘 사용 내용이 모두 동일함
		}
		
		ws.send(JSON.stringify(message));
		$(evt.target).val('');
		
		
		if(message.code == 3){
			print(message.sender, message.content, 'me', 'msg', message.creationDate);
		}else if(message.code == 4){
			printEmoticon(message.sender, message.content, 'me', 'msg', message.creationDate);
		}
		
	}
});


function scrollList(){
	$('#list').scrollTop( $('#list')[0].scrollHeight + 1000 );  
	// 현재 문서의 최대 높이보다 내려가면 제일 밑으로 내려감 // +값은 얼마여도 상관없음
	// https://devbirdfeet.tistory.com/228 
	
}

function printEmoticon(name, msg, side, state, time){
	let temp = `
		<div class="item ${state} ${side}">
			<div>
				<div>${name}</div>
				<div style="background-color:#FFF; border:0;">
					<img src="/img/chat/${msg}.png"> 
				</div>					
			</div>
			<div>${time}</div>
		</div>
	
	`;
	
	$('#list').append(temp);
	setTimeout(scrollList(), 100);
	
}
	
