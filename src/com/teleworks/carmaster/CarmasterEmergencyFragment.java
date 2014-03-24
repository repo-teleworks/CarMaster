package com.teleworks.carmaster;

import java.io.IOException;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.support.v4.app.Fragment;

public class CarmasterEmergencyFragment extends Fragment {
	public static final String ARG_OBJECT = "object";
	private Button mButton;
	private Result gcmResult; // GCM Result(���� ����)
	private Message gcmMessage; // GCM Message
	private Sender gcmSender; // GCM Sender
	// ������ �ֿܼ��� �߱޹��� API Key
	private static String API_KEY = "AIzaSyCKm5x-70QSdmg6yM1njsRq2Ef0gUXQI8w";
	// �޼����� ���� ID(?)������ �����ϸ� �˴ϴ�. �޼����� �ߺ������� ���� ���� �������� �����մϴ�
	private static String COLLAPSE_KEY = String
			.valueOf(Math.random() % 100 + 1);
	// ��Ⱑ Ȱ��ȭ ������ �� ������ ������.
	private static boolean DELAY_WHILE_IDLE = true;
	// ��Ⱑ ��Ȱ��ȭ ������ �� GCM Storage�� �����Ǵ� �ð�
	private static int TIME_TO_LIVE = 3;
	// �޼��� ���� ���н� ��õ��� Ƚ��
	private static int RETRY = 3;

	// teleworks
	// private String registrationId =
	// "APA91bGj2FpmthOi6q-B_ZL2nEGRe80jIsAHEv9s1VI39gRnTSb1wkodTjBEwWRC6G2zK8eb3zp-M3hiB_2BUpV0Hho7rVVLkyA6uN9Zsv6NsAM8Txnx13bapT_U8B6EPXqaF7ZO1Y7QBY8HiZl5X8UP3l4OBFSuZg";

	// lg g pro
	private String registrationId = "APA91bHUQ55h8DwDM5DgImjEkVjkWaz10KM7FG-nFU62jIHjgiGoeTLiRoZ2H-mZBtx4jdpiLJzeANGLey41E57yOW2BBufBLlxhJGXiz8zzwdYgm3Kd9di8h51RwGqPWxjfD0kWFZ69iBh_xQdPqqy3QyNmOrq1TA";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView;
		rootView = inflater.inflate(R.layout.fragment_emergency, container,
				false);

		mButton = (Button) rootView.findViewById(R.id.btn_em_1);
		mButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

			}
		});
		mButton = (Button) rootView.findViewById(R.id.btn_em_2);
		mButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				new Thread(new GsmSendThread()).start();
			}
		});
		return rootView;
	}

	class GsmSendThread extends Thread {
		public void run() {
			setMessage();
			sendMessage();
		}
	}

	public void setMessage() {
		gcmSender = new Sender(API_KEY);
		gcmMessage = new Message.Builder().collapseKey(COLLAPSE_KEY)
				.delayWhileIdle(DELAY_WHILE_IDLE).timeToLive(TIME_TO_LIVE)
				.addData("ticker", "Teleworks UTIS")
				.addData("title", "UTIS Emergency")
				.addData("msg", "TEST MESSAGE").build();
	}

	public void sendMessage() {
		// �ϰ����۽ÿ� ���
		// �������۽ÿ� ���
		try {
			gcmResult = gcmSender.send(gcmMessage, registrationId, RETRY);
		} catch (IOException e) {
			Log.w("sendMessage", "IOException " + e.getMessage());
		}
		Log.w("sendMessage",
				"getCanonicalIds : " + gcmResult.getCanonicalRegistrationId()
						+ "\n" + "getMessageId : " + gcmResult.getMessageId());
	}
}