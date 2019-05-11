var stompClient = null;
var page=0;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    $("#send").prop("disabled", !connected);
    $("#name").prop("disabled", !connected);
    $("#older-posts").prop('disabled', false);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    //$("#greetings").html("");
}

function retrieveOldPosts(page){
	$.get("/posts/old/"+page, function(data, status){
			if(data ==""){ 
				$("#older-posts").prop('disabled', true);}
			$.each(data, function( index, post ) {
			 // console.log( index + ": " + value.content );
			prePendPostToChat(post);
			});
	});
}

function olderPosts(){
	retrieveOldPosts(page++);
}



function appendPostToChat(post) {
	console.log("post :"+post);
	 $("#greetings").append("<tr><td>" + post.createDateTime  + " :" + post.userName + " : " + post.content + "</td></tr>");
}

function prePendPostToChat(post) {
	console.log("post :"+post);
	 $("#greetings").prepend("<tr><td>" + post.createDateTime  + " :" + post.userName + " : " + post.content + "</td></tr>");
}


function connect() {
    retrieveOldPosts(page++);
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({"content":"eee"}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic_toto/greetings', function (greeting) {
            appendPostToChat(JSON.parse(greeting.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}



$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
    $( "#older-posts" ).click(function() { olderPosts(); });
    $("#older-posts").prop('disabled', true);
});

