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
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Form2 form2 = new Form2();
            form2.Show();
            this.Hide();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        int i = 0;
        private void pictureBox2_Click(object sender, EventArgs e)
        {
            i++;
            if (i == 1)
                pictureBox3.Load("SuperMario.gif");
            if (i == 2)
                pictureBox3.Load("Pokemon.gif");
            if (i == 3)
                pictureBox3.Load("CarRace.gif");
            if (i == 4)
                pictureBox3.Load("DuckHunt.gif");
            if(i == 5)
            {
                pictureBox3.Load("Static.gif");
                i = 0;
            }

        }
    }
}
