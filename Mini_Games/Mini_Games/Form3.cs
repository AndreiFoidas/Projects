using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Mini_Games
{
    public partial class Form3 : Form
    {
        public Form3()
        {
            InitializeComponent();
        }

        int nr,j,scor;
        string lit, sir,but;

        public string CreeareSecventa()
        {
            Random rnd = new Random();
            nr = rnd.Next(1, 5);
            lit = nr.ToString();
            return lit;
        }

        private new void Click(object sender, EventArgs e)
        {
            Button obj = (Button)sender;
            but = obj.Text;

            if(but[0]=='1')
            {
                System.Media.SoundPlayer player = new System.Media.SoundPlayer("re.wav");
                player.Play();
            }
            if (but[0] == '2')
            {
                System.Media.SoundPlayer player = new System.Media.SoundPlayer("fa.wav");
                player.Play();
            }
            if (but[0] == '3')
            {
                System.Media.SoundPlayer player = new System.Media.SoundPlayer("la.wav");
                player.Play();
            }
            if (but[0] == '4')
            {
                System.Media.SoundPlayer player = new System.Media.SoundPlayer("do-octave.wav");
                player.Play();
            }


            if (but[0]!=sir[j])
            {
                System.Media.SoundPlayer player = new System.Media.SoundPlayer("Incorrect.wav");
                player.Play();

                sir = "";
                timer5.Interval = 1000;
                timer1.Interval = 450;
                timer2.Interval = 450;
                timer3.Interval = 450;
                timer4.Interval = 450;
                MessageBox.Show("Ai pierdut! Scorul tau este: " + scor);
                scor = -1;
                i = 0; j = 0;
            }
            else
            {
                j++;
            }
            if(sir.Length==j)
            {
                scor++;
                if (scor % 3 == 0 && timer5.Interval>301)
                {
                    timer5.Interval-=100;
                    timer1.Interval -= 50;
                    timer2.Interval -= 50;
                    timer3.Interval -= 50;
                    timer4.Interval -= 50;
                }
                    

                label1.Text = scor.ToString();
                sir = sir + CreeareSecventa();
                i = 0; j = 0;
                timer5.Enabled = true;
            }
                
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            if(button1.BackColor==Color.Lime)
            {
                button1.BackColor = Color.White;
                button1.ForeColor = Color.White;

                System.Media.SoundPlayer player = new System.Media.SoundPlayer("re.wav");
                player.Play();
            }
            else
            {
                button1.BackColor = Color.Lime;
                button1.ForeColor = Color.Lime;
                timer1.Enabled = false;
            }
        }

        private void timer2_Tick(object sender, EventArgs e)
        {
            if (button2.BackColor == Color.Red)
            {
                button2.BackColor = Color.White;
                button2.ForeColor = Color.White;

                System.Media.SoundPlayer player = new System.Media.SoundPlayer("fa.wav");
                player.Play();
            }
            else
            {
                button2.BackColor = Color.Red;
                button2.ForeColor = Color.Red;
                timer2.Enabled = false;
            }
        }

        private void timer3_Tick(object sender, EventArgs e)
        {
            if (button3.BackColor == Color.Yellow)
            {
                button3.BackColor = Color.White;
                button3.ForeColor = Color.White;

                System.Media.SoundPlayer player = new System.Media.SoundPlayer("la.wav");
                player.Play();
            }
            else
            {
                button3.BackColor = Color.Yellow;
                button3.ForeColor = Color.Yellow;
                timer3.Enabled = false;
            }
        }

        private void timer4_Tick(object sender, EventArgs e)
        {
            if (button4.BackColor == Color.Blue)
            {
                button4.BackColor = Color.White;
                button4.ForeColor = Color.White;

                System.Media.SoundPlayer player = new System.Media.SoundPlayer("do-octave.wav");
                player.Play();
            }
            else
            {
                button4.BackColor = Color.Blue;
                button4.ForeColor = Color.Blue;
                timer4.Enabled = false;
            }
        }

        int i;
        private void timer5_Tick(object sender, EventArgs e)
        {
            
            if(i<sir.Length)
            {
                
                if (sir[i] == '1')
                {
                    timer1.Enabled = true;
                }
                if (sir[i] == '2')
                {
                    timer2.Enabled = true;
                }
                if (sir[i] == '3')
                {
                    timer3.Enabled = true;
                }
                if (sir[i] == '4')
                {
                    timer4.Enabled = true;
                }
                i++;
            }
            if (i == sir.Length)
                timer5.Enabled = false;
        }

        private void button5_Click(object sender, EventArgs e)
        {
            button5.Visible = false;
            sir = sir + CreeareSecventa();
            i = 0;
            timer5.Enabled = true;
        }
    }
}
