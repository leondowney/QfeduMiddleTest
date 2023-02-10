package TCPmessage;

import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Chatclient extends JFrame {

    TextField tftext = new TextField(50);
    static TextArea tfarea = new TextArea();
    JButton button = new JButton("发送");
    static JButton button2 = new JButton("关闭");
    Color c1 = new Color(000, 254, 007);
    static Socket s1;
    static DataOutputStream dos;
    static ImageIcon icon;
    Image image;
    public static void main(String[] args) {
        new Chatclient().launchFrame();

// InputStream in;
// OutputStream os;
        try {

            OutputStream os = s1.getOutputStream();
            dos = new DataOutputStream(os);
            InputStream in = s1.getInputStream();
            DataInputStream dis = new DataInputStream(in);
            while (true) {
                String ii = dis.readUTF();
                System.out.println(ii);

                if (ii != null) {

                    tfarea.append("Server说:" + ii + "\n");
                }
            }
        } catch (IOException e1) {

            e1.printStackTrace();
        }
    }

    public void launchFrame() {
        JPanel panel = new JPanel()
        {
            protected void paintComponent(Graphics g)
            {


                g.drawImage(icon.getImage(), 0, 0, null);

                super.paintComponent(g);
            }
        };

        panel.setOpaque( false );
        panel.setPreferredSize( new Dimension(510,285));
        setLocation(400, 300);
        this.setSize(450, 280);
        this.setTitle("作者：(太原千锋Java2005班)梁东宇------聊天客户端");
        Panel p = new Panel();
        Panel p2 = new Panel();
        Panel p3 = new Panel();
        Panel p4 = new Panel();
// this.add(new ImageView((Element) image));
        p.add(new JLabel("对话框"));
        p.add(tfarea);
        p2.add(new JLabel("输入框"));
        p2.add(tftext);
        p3.add(button);
        p3.add(button2);
        panel.add(p,BorderLayout.NORTH);
        panel.add(p2,BorderLayout.CENTER);
        panel.add(p3,BorderLayout.SOUTH);
        p4.add(new JLabel("@copyright梁东宇"));
        this.add(p4,BorderLayout.SOUTH);
        this.add(panel);
//tfarea.setBackground(c1);
        Monitor1 m1 = new Monitor1();
        button2.addActionListener((ActionListener) m1);
        pack();
        this.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                System.exit(0);

            }

        });
        tftext.addActionListener(new TFlistener());
        button.addActionListener(new TFlistener());
        setVisible(true);
        tfarea.append("客户端已启动.........."+"\n");
        connect();

    }

    public void connect() {

        try {
            s1 = new Socket("127.0.0.1", 8080);
        } catch (UnknownHostException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public class TFlistener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String s = tftext.getText().trim();
            tfarea.append("自己:   " + s + "\n");
            tftext.setText("");

            try {

                dos.writeUTF(s);
                dos.flush();
// dos.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

    }
}

