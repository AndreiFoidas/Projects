using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Mini_Games
{
    public partial class Form4 : Form
    {
        public Form4()
        {
            InitializeComponent();
        }

        private void Form4_Load(object sender, EventArgs e)
        {
            this.Bounds = Screen.PrimaryScreen.Bounds;
            pictureBox1.Top = panel1.Bottom - 75;
            label1.Left = panel1.Bounds.Right - 110;

        }

        int scor = 0, vieti = 3;
        int nr1 = 4, poza1, item1 = 2;

        private void timer1_Tick(object sender, EventArgs e)
        {
            pictureBox1.Left = Cursor.Position.X - (pictureBox1.Width / 2);

            a1_drop.Visible = true;
            if (scor >= 5)
                timer2.Enabled = true;
            if (scor >= 15)
                timer3.Enabled = true;

            a1_drop.Top = a1_drop.Top + nr1;
            if (a1_drop.Bottom >= pictureBox1.Top && a1_drop.Bottom <= pictureBox1.Bottom &&
                a1_drop.Left >= pictureBox1.Left && a1_drop.Right <= pictureBox1.Right)
            {
                if (item1 == 2)
                {
                    scor++;
                    label1.Text = "Scor: " + scor;
                }
                if (item1 == 3)
                {
                    if (vieti != 3)
                    {
                        label1.Text = "Scor: " + scor;
                        if (vieti == 0)
                        {
                            vieti = 1;
                            heart1.Load("Heart.png");
                        }
                        else
                        {
                            if (vieti == 1)
                            {
                                vieti = 2;
                                heart2.Load("Heart.png");
                            }
                            else
                            {
                                if (vieti == 2)
                                {
                                    vieti = 3;
                                    heart3.Load("Heart.png");
                                }
                            }
                        }

                    }
                    else
                    {
                        scor += 3;
                        label1.Text = "Scor: " + scor;
                    }
                }
                if (item1 == 1)
                {
                    if (vieti == 3)
                    {
                        heart3.Load("upscale-239494558011212.png");
                        vieti--;
                    }
                    else
                    {
                        if (vieti == 2)
                        {
                            heart2.Load("upscale-239494558011212.png");
                            vieti--;
                        }
                        else
                        {
                            if (vieti == 1)
                            {
                                heart1.Load("upscale-239494558011212.png");
                                vieti--;
                            }
                            else
                            {
                                if (vieti == 0)
                                {
                                    timer1.Enabled = timer2.Enabled = timer3.Enabled = false;
                                    button1.Top = 350;
                                    button1.Left = 625;
                                    button1.Visible = true;
                                    MessageBox.Show("Ai ramas fara vieti! Scorul tau este: " + scor);

                                }
                            }
                        }
                    }
                }

                a1_drop.Top = panel1.Top - 10;
                Random rnd = new Random();
                int x;
                x = rnd.Next(5, this.Bounds.Width - 30);
                a1_drop.Left = x;
                if (nr1 < 20)
                    nr1 += 1;

                Random rnd2 = new Random();
                poza1 = rnd2.Next(1, 100);

                if (poza1 <= 20)
                {
                    a1_drop.Load("Brick.png");
                    item1 = 1;
                }

                if (poza1 > 35 && poza1 <= 100)
                {
                    a1_drop.Load("Apple.png");
                    item1 = 2;
                }

                if (poza1 > 20 && poza1 <= 35)
                {
                    a1_drop.Load("GoldenApple.png");
                    item1 = 3;
                }
            }
            else
            {
                if (a1_drop.Top > panel1.Bottom - 70)
                {

                    a1_drop.Top = panel1.Top - 10;
                    Random rnd = new Random();
                    int x;
                    x = rnd.Next(5, this.Bounds.Width - 30);
                    a1_drop.Left = x;

                    if (item1 == 2)
                    {
                        if (vieti == 3)
                        {
                            heart3.Load("upscale-239494558011212.png");
                            vieti--;
                        }
                        else
                        {
                            if (vieti == 2)
                            {
                                heart2.Load("upscale-239494558011212.png");
                                vieti--;
                            }
                            else
                            {
                                if (vieti == 1)
                                {
                                    heart1.Load("upscale-239494558011212.png");
                                    vieti--;
                                }
                                else
                                {
                                    if (vieti == 0)
                                    {
                                        timer1.Enabled = timer2.Enabled = timer3.Enabled = false;
                                        button1.Top = 350;
                                        button1.Left = 625;
                                        button1.Visible = true;
                                        MessageBox.Show("Ai ramas fara vieti! Scorul tau este: " + scor);

                                    }
                                }
                            }
                        }
                    }

                    if (poza1 <= 20)
                    {
                        a1_drop.Load("Brick.png");
                        item1 = 1;
                    }

                    if (poza1 > 35 && poza1 <= 100)
                    {
                        a1_drop.Load("Apple.png");
                        item1 = 2;
                    }

                    if (poza1 > 20 && poza1 <= 35)
                    {
                        a1_drop.Load("GoldenApple.png");
                        item1 = 3;
                    }

                }
            }

        }

        int nr2 = 6, poza2, item2 = 2;

        private void timer2_Tick(object sender, EventArgs e)
        {

            a2_drop.Visible = true;
            Random rnd2 = new Random();
            poza2 = rnd2.Next(1, 100);

            a2_drop.Top = a2_drop.Top + nr2;
            if (a2_drop.Bottom >= pictureBox1.Top && a2_drop.Bottom <= pictureBox1.Bottom &&
                a2_drop.Left >= pictureBox1.Left && a2_drop.Right <= pictureBox1.Right)
            {
                if (item2 == 2)
                {
                    scor++;
                    label1.Text = "Scor: " + scor;
                }
                if (item2 == 3)
                {
                    if (vieti != 3)
                    {
                        label1.Text = "Scor: " + scor;
                        if (vieti == 0)
                        {
                            vieti = 1;
                            heart1.Load("Heart.png");
                        }
                        else
                        {
                            if (vieti == 1)
                            {
                                vieti = 2;
                                heart2.Load("Heart.png");
                            }
                            else
                            {
                                if (vieti == 2)
                                {
                                    vieti = 3;
                                    heart3.Load("Heart.png");
                                }
                            }
                        }

                    }
                    else
                    {
                        scor += 3;
                        label1.Text = "Scor: " + scor;
                    }
                }
                if (item2 == 1)
                {
                    if (vieti == 3)
                    {
                        heart3.Load("upscale-239494558011212.png");
                        vieti--;
                    }
                    else
                    {
                        if (vieti == 2)
                        {
                            heart2.Load("upscale-239494558011212.png");
                            vieti--;
                        }
                        else
                        {
                            if (vieti == 1)
                            {
                                heart1.Load("upscale-239494558011212.png");
                                vieti--;
                            }
                            else
                            {
                                if (vieti == 0)
                                {
                                    timer1.Enabled = timer2.Enabled = timer3.Enabled = false;
                                    button1.Top = 350;
                                    button1.Left = 625;
                                    button1.Visible = true;
                                    MessageBox.Show("Ai ramas fara vieti! Scorul tau este: " + scor);

                                }
                            }
                        }
                    }
                }

                a2_drop.Top = panel1.Top - 10;
                Random rnd = new Random();
                int x;
                x = rnd.Next(5, this.Bounds.Width - 30);
                a2_drop.Left = x;
                if (nr2 < 20)
                    nr2 += 1;


                if (poza2 <= 20)
                {
                    a2_drop.Load("Brick.png");
                    item2 = 1;
                }

                if (poza2 > 35 && poza2 <= 100)
                {
                    a2_drop.Load("Apple.png");
                    item2 = 2;
                }

                if (poza2 > 20 && poza2 <= 35)
                {
                    a2_drop.Load("GoldenApple.png");
                    item2 = 3;
                }
            }
            else
            {
                if (a2_drop.Top > panel1.Bottom - 70)
                {

                    a2_drop.Top = panel1.Top - 10;
                    Random rnd = new Random();
                    int x;
                    x = rnd.Next(5, this.Bounds.Width - 30);
                    a2_drop.Left = x;

                    if (item2 == 2)
                    {
                        if (vieti == 3)
                        {
                            heart3.Load("upscale-239494558011212.png");
                            vieti--;
                        }
                        else
                        {
                            if (vieti == 2)
                            {
                                heart2.Load("upscale-239494558011212.png");
                                vieti--;
                            }
                            else
                            {
                                if (vieti == 1)
                                {
                                    heart1.Load("upscale-239494558011212.png");
                                    vieti--;
                                }
                                else
                                {
                                    if (vieti == 0)
                                    {

                                        timer1.Enabled = timer2.Enabled = timer3.Enabled = false;
                                        button1.Top = 350;
                                        button1.Left = 625;
                                        button1.Visible = true;
                                        MessageBox.Show("Ai ramas fara vieti! Scorul tau este: " + scor);

                                    }
                                }
                            }
                        }
                    }

                    if (poza2 <= 20)
                    {
                        a2_drop.Load("Brick.png");
                        item2 = 1;
                    }

                    if (poza2 > 35 && poza2 <= 100)
                    {
                        a2_drop.Load("Apple.png");
                        item2 = 2;
                    }

                    if (poza2 > 20 && poza2 <= 35)
                    {
                        a2_drop.Load("GoldenApple.png");
                        item2 = 3;
                    }

                }
            }

        }

        int nr3 = 8, poza3, item3 = 2;

        private void timer3_Tick(object sender, EventArgs e)
        {
            a3_drop.Visible = true;

            Random rnd2 = new Random();
            poza3 = rnd2.Next(1, 100);

            a3_drop.Top = a3_drop.Top + nr3;
            if (a3_drop.Bottom >= pictureBox1.Top && a3_drop.Bottom <= pictureBox1.Bottom &&
                a3_drop.Left >= pictureBox1.Left && a3_drop.Right <= pictureBox1.Right)
            {
                if (item3 == 2)
                {
                    scor++;
                    label1.Text = "Scor: " + scor;
                }
                if (item3 == 3)
                {
                    if (vieti != 3)
                    {
                        label1.Text = "Scor: " + scor;
                        if (vieti == 0)
                        {
                            vieti = 1;
                            heart1.Load("Heart.png");
                        }
                        else
                        {
                            if (vieti == 1)
                            {
                                vieti = 2;
                                heart2.Load("Heart.png");
                            }
                            else
                            {
                                if (vieti == 2)
                                {
                                    vieti = 3;
                                    heart3.Load("Heart.png");
                                }
                            }
                        }

                    }
                    else
                    {
                        scor += 3;
                        label1.Text = "Scor: " + scor;
                    }
                }
                if (item3 == 1)
                {
                    if (vieti == 3)
                    {
                        heart3.Load("upscale-239494558011212.png");
                        vieti--;
                    }
                    else
                    {
                        if (vieti == 2)
                        {
                            heart2.Load("upscale-239494558011212.png");
                            vieti--;
                        }
                        else
                        {
                            if (vieti == 1)
                            {
                                heart1.Load("upscale-239494558011212.png");
                                vieti--;
                            }
                            else
                            {
                                if (vieti == 0)
                                {
                                    timer1.Enabled = timer2.Enabled = timer3.Enabled = false;
                                    button1.Top = 350;
                                    button1.Left = 625;
                                    button1.Visible = true;
                                    MessageBox.Show("Ai ramas fara vieti! Scorul tau este: " + scor);

                                }
                            }
                        }
                    }
                }

                a3_drop.Top = panel1.Top - 10;
                Random rnd = new Random();
                int x;
                x = rnd.Next(5, this.Bounds.Width - 30);
                a3_drop.Left = x;
                if (nr3 < 20)
                    nr3 += 1;


                if (poza3 <= 20)
                {
                    a3_drop.Load("Brick.png");
                    item3 = 1;
                }

                if (poza3 > 35 && poza3 <= 100)
                {
                    a3_drop.Load("Apple.png");
                    item3 = 2;
                }

                if (poza3 > 20 && poza3 <= 35)
                {
                    a3_drop.Load("GoldenApple.png");
                    item3 = 3;
                }
            }
            else
            {
                if (a3_drop.Top > panel1.Bottom - 70)
                {

                    a3_drop.Top = panel1.Top - 10;
                    Random rnd = new Random();
                    int x;
                    x = rnd.Next(5, this.Bounds.Width - 30);
                    a3_drop.Left = x;

                    if (item3 == 2)
                    {
                        if (vieti == 3)
                        {
                            heart3.Load("upscale-239494558011212.png");
                            vieti--;
                        }
                        else
                        {
                            if (vieti == 2)
                            {
                                heart2.Load("upscale-239494558011212.png");
                                vieti--;
                            }
                            else
                            {
                                if (vieti == 1)
                                {
                                    heart1.Load("upscale-239494558011212.png");
                                    vieti--;
                                }
                                else
                                {
                                    if (vieti == 0)
                                    {
                                        timer1.Enabled = timer2.Enabled = timer3.Enabled = false;
                                        button1.Top = 350;
                                        button1.Left = 625;
                                        button1.Visible = true;
                                        MessageBox.Show("Ai ramas fara vieti! Scorul tau este: " + scor);

                                    }
                                }
                            }
                        }
                    }

                    if (poza3 <= 20)
                    {
                        a3_drop.Load("Brick.png");
                        item3 = 1;
                    }

                    if (poza3 > 35 && poza3 <= 100)
                    {
                        a3_drop.Load("Apple.png");
                        item3 = 2;
                    }

                    if (poza3 > 20 && poza3 <= 35)
                    {
                        a3_drop.Load("GoldenApple.png");
                        item3 = 3;
                    }

                }
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            a2_drop.Visible = a3_drop.Visible = false;
            timer1.Enabled = true;
            scor = 0;
            label1.Text = "Scor: 0";
            nr1 = 4;
            nr2 = 6;
            nr3 = 8;
            button1.Visible = false;
            vieti = 3;
            heart1.Load("Heart.png");
            heart2.Load("Heart.png");
            heart3.Load("Heart.png");
            a1_drop.Top = panel1.Top - 10;
            a2_drop.Top = panel1.Top - 10;
            a3_drop.Top = panel1.Top - 10;
        }
    }
}
