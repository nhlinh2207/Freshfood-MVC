$(document).ready(function(){
    $('#list-active-chatroom').load('/freshfood/chat-list-admin div#chat-list');
});

var chatRoomId= null;
var stompClient = null;
var socket = null;
var buttonStatus = false;
var btnSend = $("#send-message");
var inputMessage = $("#input-message");
var newMessages = $("#chatbox-messages");
const chatContent = document.querySelector('.chatbox__support');

function connectChatRoom(element){
    chatRoomId = $(element).attr("chatroom-id");

    chatContent.classList.remove('chatbox--close')
    chatContent.classList.add('chatbox--active');
    connect();
    btnSend.on("click", sendMessage);
}

function closeChatBox(){
    if (stompClient != null) {
        newMessages.html("");
        chatContent.classList.remove('chatbox--active')
        chatContent.classList.add('chatbox--close');
        stompClient.disconnect();
    }
}

function connect(){
    // Close other chat box
    if (stompClient != null) {
        newMessages.html("");
        stompClient.disconnect();
    }

    socket = new SockJS("/ws");
    stompClient = Stomp.over(socket);
    stompClient.connect({ 'chatRoomId' : chatRoomId }, stompSuccess, stompFailure);
}

function disconnect() {
    if (stompClient != null) {
        newMessages.html("");
        stompClient.disconnect();
    }
}

function  stompSuccess(){
    stompClient.subscribe('/user/queue/' + chatRoomId + '.old.messages', oldMessages);
    stompClient.subscribe('/topic/' + chatRoomId + '.public.messages', publicMessages);
    loadOldMessages();
}

function stompFailure(){
    alert("Failed")
}

function loadOldMessages(){
    stompClient.send("/chatroom/old.messages", {});
}

function oldMessages(response){
    var instantMessages = JSON.parse(response.body);

    $.each(instantMessages, function(index, instantMessage) {
        appendPublicMessage(instantMessage);
    });
    scrollDownMessagesPanel();
}

function appendPublicMessage(instantMessage) {
    if (instantMessage.senderType == "ADMIN") {
        newMessages
            .append('<div class="messages__item messages__item--operator">' +
                '<p style="margin: 0">'+instantMessage.content+'</p>' +
                '</div>');
    } else {
        newMessages
            .append('<div class="messages__item messages__item--visitor">' +
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
        'senderType' : 'ADMIN'
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