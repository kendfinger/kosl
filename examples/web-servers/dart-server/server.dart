import "dart:io";

Future<void> main() async {
  var server = await HttpServer.bind("0.0.0.0", 8080);
  print("[Listen] 0.0.0.0:8080");
  await for (var request in server) {
    if (request.connectionInfo != null) {
      print("[Source ${request.connectionInfo?.remoteAddress.host}] ${request.method} ${request.requestedUri}");      
    } else {
      print("[Source Unknown] ${request.method} ${request.requestedUri}");      
    }

    if (request.uri.path == "/healthz") {
      request.response
        ..writeln("Success.")
        ..close();
      continue;
    }

    request.response
      ..headers.set("Access-Control-Allow-Origin", "*")
      ..writeln("Hello World")
      ..close();
  }
}
