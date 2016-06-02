<html>
<head>
    <meta charset="UTF-8"/>
    <title>Jetty WebSocket Chat</title>
    <script type="text/javascript">
        var ws;

        function init() {
            ws = new WebSocket("ws://localhost:8081/chat");
            ws.onopen = function (event) {

            }
            ws.onmessage = function (event) {
                var $textarea = document.getElementById("messages");
                $textarea.value = $textarea.value + event.data + "\n";
            }
            ws.onclose = function (event) {

            }
        };

        function sendMessage() {
            var messageField = document.getElementById("message");
            var message = ":" + messageField.value;
            ws.send(message);
            messageField.value = '';
        }
    </script>
</head>
<body onload="init();">
<div id="body">
    <div id="menu">
        <p class="welcome">
            Welcome, ${username}
        </p>

        <div style="clear: both"></div>
    </div>

    <div id="chatbox">
        <textarea id="messages" rows="20" cols="50" readonly="readonly"></textarea>
    </div>

    <form name="message" action="">
        <input name="usermsg" type="text" id="message" size="40"/>
        <input type="button" name="submitmsg" value="Send..." onclick="sendMessage();"/>
    </form>
</div>
</body>
</html>