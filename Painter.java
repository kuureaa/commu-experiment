import java.awt.*;				//�O���t�B�N�X�֌W
import java.awt.event.*;
import javax.swing.*;

public class Painter extends JFrame{
	Face face[] = new Face[10];
	int count = 0;
	JButton button;
	JTextField text;

	/***** main *****/
	public static void main(String[] args){
		Painter w = new Painter();  //�C���X�^���X�̐���
		w.setVisible(true);  //�E�B���h�E�̕\��
	}

	/***** �R���X�g���N�^ *****/
	public Painter(){
		setBackground(Color.white);  //�o�b�N�O���E���h�F�𔒂�
		setSize(500,500);	 //��ʃT�C�Y�̏����l

		text = new JTextField("�\�����܂���", 10);

		button = new JButton("New painting");
		button.addMouseListener(new ButtonListener());

		JPanel p = new JPanel();
		p.add(text);
		p.add(button);

		getContentPane().add(p,FlowLayout.LEFT);

		//�}�E�X�C�x���g��L���ɂ���
		MyListener myListener = new MyListener();
		addMouseListener(myListener);

		MyMotionListener myMotionListener = new MyMotionListener();
		addMouseMotionListener(myMotionListener);

		//�~�{�^���������ꂽ��, �v���O�������I������
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/***** paint *****/
	public void paint(Graphics g){
		int max;

		if(count != 0){
			if(count > 10){
				max = 10;
			} else{
				max = count;
			}
			for(int i = 0; i < max; i++){
				face[i].make(g);
			}
		}
	}

	/*** �}�E�X�̃C�x�����󂯎������N���X ***/
	class MyListener extends MouseAdapter{
		int size;
		int number = 0;

		public void mousePressed(MouseEvent e) {

			if(button.getText().equals("Change")){

				for(int i = 0; i < count; i++){
					size =
					(int)Math.hypot(e.getX() - face[i].getXAxis(),
					e.getY() - face[i].getYAxis());
					if(size <= face[i].getSize()/2){
						number = i + 1;
						text.setText("No." + number + " was selected");
					}
				}
			}

			if(button.getText().equals("New painting")){
				if(++count < 11){
					System.out.println("\n\n************ "+ count +" ****************\n\n");
					face[count - 1] = new Face(0, e.getX(), e.getY());
				} else {
					System.out.println("����ȏ�`���܂���");
				}
			}
		}
	}

	/*** �}�E�X���[�V�����̃C�x���g���󂯎������N���X ***/
	class MyMotionListener extends MouseMotionAdapter {
		int size;

		public void mouseDragged(MouseEvent e) {
			if(count < 11){
				size =
				(int)Math.hypot(e.getX() - face[count - 1].getXAxis(),
				e.getY() - face[count - 1].getYAxis());
				face[count - 1].setSize(size);
				System.out.println(size);
				repaint();
			}
		}
	}

	class ButtonListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			switch(button.getText()){
				case "New painting":
				button.setText("Change");
				break;

				case "Change":
				button.setText("New painting");
				break;
			}
		}
	}
}
