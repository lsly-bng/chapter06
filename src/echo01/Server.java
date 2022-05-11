package echo01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {

		// 서버 소켓 객체 생성 방법
		ServerSocket serverSocket = new ServerSocket();
		// 외워야함 --> ip --> cmd -> ipconfig
		serverSocket.bind(new InetSocketAddress("IP주소입력란", 10001));

		System.out.println("<서버 시작>");
		System.out.println("===============================");
		System.out.println("[연결을 기다리고 있습니다.]");

		Socket socket = serverSocket.accept();
		System.out.println("[클라이언트가 연결 되었습니다.]");

		// 메세지 받기용 스트림
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);

		// 메세지 보내기용 스트림
		OutputStream os = socket.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);

		while (true) {
			// 메세지 받기
			String msg = br.readLine();
			System.out.println("받은메세지: " + msg);

			if (msg == null) {
				break;
			}

			// 메세지 보내기
			bw.write(msg);
			bw.newLine();
			bw.flush();
		}
		System.out.println("===============================");
		System.out.println("<서버 종료>");

		br.close();
		bw.close();
		socket.close();
		serverSocket.close();
	}

}
