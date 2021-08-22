import http.server
import socketserver
from http import HTTPStatus


class HelloHandler(http.server.SimpleHTTPRequestHandler):
    def do_GET(self):
        self.send_response(HTTPStatus.OK)
        self.end_headers()
        self.wfile.write(b'Hello World\n')


httpd = socketserver.TCPServer(('', 8080), HelloHandler)
httpd.serve_forever()
