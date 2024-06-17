package at.czlabinger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientConnection implements Runnable{

    private final Socket client;

    public ClientConnection(Socket s) {
        this.client = s;
    }

    @Override
    public void run() {
        ObjectInputStream is = null;
        ObjectOutputStream os = null;
        try {
            is = new ObjectInputStream(client.getInputStream());
            os = new ObjectOutputStream(client.getOutputStream());
            QuestionHandler qh = new QuestionHandler();


            for(int i = 0; i < 3; i++) {
                System.out.println(qh.getQuestion(i));
                os.writeObject(qh.getQuestion(i));

                String str = (String) is.readObject();
                System.out.println("Read string: " + str);
                if(!qh.check(str, i)) {
                    os.writeObject("Falsch! Richtig ware " + qh.getAnswer(i));
                } else {
                    os.writeObject("Richtig!");
                }
            }
            os.writeObject(qh.getCorrect() + "/3 richtig beantwortet");

            os.close();
            is.close();
            client.close();
        }catch (ClassNotFoundException | IOException e){
            e.printStackTrace();
        } finally {
            try {
                is.close();
                os.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
