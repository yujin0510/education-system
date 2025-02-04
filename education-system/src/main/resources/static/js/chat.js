const socket = new SockJS('http://localhost:8090/ws-stomp'); // socket 연결 경로
		const stompClient = Stomp.over(socket);

		// 사용자 이름 입력 받기
		let username = document.getElementById('username').innerHTML;

		// 현재 사용자 수
		let n = 0;

		// 메시지 입력란에 자동으로 커서 위치
		document.getElementById('messageInput').focus();

		// 접속 후 사용자 이름 설정
		stompClient.connect({}, function (frame) {
			
			// 최초 메시지 전송 (채팅 입장)
			stompClient.send("/pub/message", {}, JSON.stringify({
				type: "SEND", // 고정된 타입
				contents: username + "님이 채팅에 입장했습니다.", // 입장 메시지
				sender: username,
				timestamp: new Date().toLocaleTimeString(),
				code: 1
			}));

			// 메시지 수신
			stompClient.subscribe('/sub', function (message) {
				let receivedMessage = JSON.parse(message.body); // 메시지 파싱
				displayMessage(receivedMessage); // 화면에 메시지 표시
			});
		});

		// 페이지를 떠날 때 퇴장 메시지 전송
		window.addEventListener('beforeunload', function () {

			// 퇴장 메시지 먼저 전송
			stompClient.send("/pub/message", {}, JSON.stringify({
				type: "SEND", // 고정된 타입
				contents: username + "님이 채팅에 퇴장했습니다.", // 퇴장 메시지
				sender: username,
				timestamp: new Date().toLocaleTimeString(),
				code: 2
			}));

			// 연결 종료
			stompClient.disconnect(function () {
				console.log("Disconnected from server.");
			});

		});

		// 메시지 전송 버튼 클릭 시
		document.getElementById('sendButton').addEventListener('click', function () {
			sendMessage();
		});

		// 엔터키로 메시지 전송
		document.getElementById('messageInput').addEventListener('keydown', function (event) {
			if (event.key === 'Enter') {
				sendMessage();
			}
		});

		// 메시지 전송 함수
		function sendMessage() {
			const contents = document.getElementById('messageInput').value;
			const message = {
				type: "SEND", // 고정된 타입
				contents: contents, // 입력된 내용
				sender: username,
				timestamp: new Date().toLocaleTimeString(),
				code: 3
			};
			stompClient.send("/pub/message", {}, JSON.stringify(message)); // 메시지 전송 경로
			document.getElementById('messageInput').value = ''; // 입력 필드 초기화
		}

		// 메시지 화면에 추가하는 함수
		function displayMessage(message) {
			const messagesDiv = document.getElementById('messages');
			const messageElement = document.createElement('div');
			messageElement.classList.add('message'); // 메시지 컨테이너

			const sender = message.sender;
			const timestamp = message.timestamp;
			const contents = message.contents;
			const code = message.code;

			// 디버깅용 로그 추가
			console.log("Received Message Object:", message);
			console.log("Message Contents:", contents);

			let messageMeta = document.createElement('div');
			messageMeta.classList.add('messageMeta');

			let messageText = document.createElement('div');
			messageText.classList.add('messageText');
			messageText.textContent = contents;
			

			// 입장/퇴장 메시지 처리
			if (code == 1 || code == 2) {
				messageMeta.textContent = ''; // timestamp와 sender를 숨김
				messageText.classList.add('enter-message'); // 입장/퇴장 메시지 스타일 적용

				console.log("Enter/Exit message detected:", contents); // 디버깅용 로그 추가
			} else if (code == 3) {
				messageMeta.textContent = `${sender} (${timestamp})`; // 보낸 사람과 발송 시간 표시
			}

			messageElement.appendChild(messageMeta); // messageMeta를 먼저 추가
			messageElement.appendChild(messageText); // messageText는 그 뒤에 추가

			messagesDiv.appendChild(messageElement);
			messagesDiv.scrollTop = messagesDiv.scrollHeight; // 스크롤을 맨 아래로
		}