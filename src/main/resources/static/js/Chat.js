$(document).ready(function(){
    var stompClient = null;
    var socket = null;
    var chatRoomId = null;
    var buttonStatus = false;
    var btnSend = $("#send-message");
    var inputMessage = $("#input-message");
    var newMessages = $("#chatbox-messages");

    function connect(){
        socket = new SockJS("/ws");
        stompClient = Stomp.over(socket);
        stompClient.connect({ 'chatRoomId' : chatRoomId }, stompSuccess, stompFailure);
    }

    function disconnect() {
        if (stompClient != null) {
            stompClient.disconnect();
        }
    }

    function  stompSuccess(){
        // stompClient.subscribe('/chatroom/connected.users', updateConnectedUsers);
        stompClient.subscribe('/chatroom/old.messages', oldMessages);
        stompClient.subscribe('/topic/' + chatRoomId + '.public.messages', publicMessages);
        // stompClient.subscribe('/user/queue/' + chatRoomId + '.private.messages', privateMessages);
        // stompClient.subscribe('/topic/' + chatRoomId + '.connected.users', updateConnectedUsers);
    }

    function stompFailure(){
        alert("Failed")
    }

    function oldMessages(response){
        var instantMessages = JSON.parse(response.body);

        $.each(instantMessages, function(index, instantMessage) {
            appendPublicMessage(instantMessage);
        });
        scrollDownMessagesPanel();
    }

    function appendPublicMessage(instantMessage) {
        if (instantMessage.fromUser == "ADMIN") {
            newMessages
                .append('<div class="messages__item messages__item--visitor">' +
                    '<p style="margin: 0">'+instantMessage.content+'</p>' +
                    '</div>');
        } else {
            newMessages
                .append('<div class="messages__item messages__item--operator">' +
                    '<p style="margin: 0">'+instantMessage.content+'</p>' +
                    '</div>');
        }
    }

    function inputMessageIsEmpty() {
        return inputMessage.val() === "";
    }

    function sendMessage() {
        var instantMessage;

        if (inputMessageIsEmpty()) {
            inputMessage.focus();
            return;
        }
        instantMessage = {
            'content' : inputMessage.val(),
            'senderType' : 'USER'
        }
        stompClient.send("/chatroom/send.message", {}, JSON.stringify(instantMessage));
        inputMessage.val("").focus();
    }

    function publicMessages(message) {
        var instantMessage = JSON.parse(message.body);
        appendPublicMessage(instantMessage);
        scrollDownMessagesPanel();
    }

    function scrollDownMessagesPanel() {
        $(".chatbox__messages").animate({"scrollTop": newMessages[0].scrollHeight}, "fast");
    }

    const chatButton = document.querySelector('.chatbox__button');
    const chatContent = document.querySelector('.chatbox__support');

    chatButton.addEventListener('click', () => {
        if(!buttonStatus) {
            //Get chatRoomId
            $.ajax({
                url: '/freshfood/chatroom/findByUser',
                type: 'GET',
                dataType: 'json',
                success: function(json){
                    chatRoomId = json;
                    console.log(chatRoomId);
                    chatContent.classList.remove('chatbox--close')
                    chatContent.classList.add('chatbox--active')
                    buttonStatus = !buttonStatus
                    // Connect socket
                    connect();
                    btnSend.on("click", sendMessage);
                },
                error: function(){
                    alert("không thành công !");
                }
            });
        }else if (buttonStatus) {
            chatContent.classList.remove('chatbox--active')
            chatContent.classList.add('chatbox--close')
            buttonStatus = !buttonStatus
            // Disconnect Socket
            disconnect();
        }
    })
})
