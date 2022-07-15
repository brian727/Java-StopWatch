import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.sql.Time;
import javax.swing.*;

public class Stopwatch implements ActionListener {

    JFrame frame = new JFrame();
    JButton startButton = new JButton("START");
    JButton stopButton = new JButton("STOP");
    JLabel timeLabel = new JLabel();
    int elapsedTime =0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started = false;
    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);

    Timer timer = new Timer(1000, new ActionListener() {
        //following is triggered by delay parameter in Timer signature
        public void actionPerformed(ActionEvent e) {
            elapsedTime=elapsedTime+1000;
            hours = (elapsedTime/3600000);
            //modulus by 60 here creates one, to convert to an hour
            minutes = (elapsedTime/60000)%60;
            seconds = (elapsedTime/1000)%60;
            seconds_string = String.format("%02d", seconds);
            minutes_string = String.format("%02d", minutes);
            hours_string = String.format("%02d", hours);
            timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
        }
    });
    Stopwatch() {

        timeLabel.setText(hours_string + ":" + minutes_string + ":" + hours_string);
        timeLabel.setBounds(100,100,200,100);
        timeLabel.setFont(new Font("Verdana", Font.PLAIN, 35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        startButton.setBounds(100,200,100,50);
        startButton.setFont(new Font("Roboto", Font.PLAIN, 20));
        startButton.setFocusable(false);
        startButton.addActionListener(this);

        frame.add(startButton);
        frame.add(timeLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==startButton) {

            if (started==false) {
                started=true;
                startButton.setText("STOP");
                start();
            } else {
                started=false;
                startButton.setText("START");
                stop();
            }
        }
    }

    void start() {
        timer.start();
    }

    void stop() {
        timer.stop();
    }
}
