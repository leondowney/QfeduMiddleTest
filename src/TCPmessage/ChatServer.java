package TCPmessage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.io.IOException;
import java.io.InputStream;

import java.io.OutputStream;

import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChatServer {

    static JFrame f = new JFrame("作者：(太原千锋Java2005班)梁东宇------聊天服务器端");
    static TextField tftext = new TextField(50);
    static TextArea tfarea = new TextArea();
    static JButton button = new JButton("发送");
    static JButton button2 = new JButton("关闭");
    Socket s1;
    InputStream in;
    DataInputStream dis;
    OutputStream os;
    static DataOutputStream dos;
    static ImageIcon icon;
    Image image;
    public ChatServer() {
        tftext.addActionListener(new TFlistener());

        button.addActionListener(new TFlistener());
    }

    public static void main(String[] args) {
        new ChatServer();
        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g)
            {
                g.drawImage(icon.getImage(), 0, 0, null);

                super.paintComponent(g);
            }
        };


        panel.setOpaque( false );
        panel.setPreferredSize( new Dimension(510,285));


        Panel p = new Panel();
        Panel p2 = new Panel();
        Panel p3 = new Panel();
        Panel p4 = new Panel();
        p.add(new JLabel("对话框"));
        p.add(tfarea);
        p2.add(new JLabel("输入框"));
        p2.add(tftext);
        p3.add(button);
        p3.add(button2);
        p4.add(new JLabel("@copyright梁东宇"));
        panel.add(p,BorderLayout.NORTH);
        panel.add(p2,BorderLayout.CENTER);
        panel.add(p3,BorderLayout.SOUTH);
        f.add(p4,BorderLayout.SOUTH);
//tfarea.setBackground(Color.green);
        f.add(panel);
        f.setSize(550, 330);
        Monitor1 m1 = new Monitor1();
        button2.addActionListener((ActionListener) m1);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        connect();

    }
    static boolean started = false;

    public static void connect() {
        try {
            ServerSocket ss = new ServerSocket(8080);

            started = true;
            while (started) {
                boolean connected = false;
                Socket s1 = ss.accept();
                connected = true;
                System.out.println("有一个客户端连接上了");
                tfarea.append("有一个客户端连接上了.........."+"\n");
// 读进
                InputStream in = s1.getInputStream();
                DataInputStream dis = new DataInputStream(in);

// 写出
                OutputStream os = s1.getOutputStream();
                dos = new DataOutputStream(os);
                while (connected) {
                    String ii = dis.readUTF();
                    System.out.println(ii);
                    if (ii != null) {
                        tfarea.append("Client说:" + ii + "\n");
                    }

                }
                s1.close();
                dis.close();

            }
        } catch (IOException e) {

            e.printStackTrace();
            e.printStackTrace();
            System.out.println("程序运行出错了");
        }

    }

    public class TFlistener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String s = tftext.getText().trim();
            tfarea.append("自己：   " + s + "\n");
            tftext.setText("");
//System.out.println("本人   ：" + s);
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

