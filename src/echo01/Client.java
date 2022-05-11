package echo01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException {

		Socket socket = new Socket();

		System.out.println("<클라이언트 시작>");
		System.out.println("===============================");
		System.out.println("[서버에 연결을 요청합니다.]");
		socket.connect(new InetSocketAddress("IP주소입력란", 10001));
		System.out.println("[서버에 연결 되었습니다.]");

		// 메세지 보내기용 스트림
		OutputStream os = socket.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);

		// 메세지 받기용 스트림
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);

		// 스캐너
		Scanner sc = new Scanner(System.in);

		while (true) {
			// 키보드 입력
			String str = sc.nextLine();

			if (str.equals("/q")) {
				break;
			}

			// 메세지 보내기
			bw.write(str);
			bw.newLine();
			bw.flush(); // 쟁반이 꽉 채워지지 않아도 내용 보내라는 의미

			// 메세지 받기
			String reMsg = br.readLine();
			System.out.println("server: [" + reMsg + "]");
		}

		System.out.println("===============================");
		System.out.println("<클라이언트 종료>");

		sc.close();
		br.close();
		bw.close();
		socket.close();
	}
}
