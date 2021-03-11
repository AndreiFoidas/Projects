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
    public partial class Form2 : Form
    {
        public Form2()
        {
            InitializeComponent();
        }

        private void pictureBox2_Click(object sender, EventArgs e)
        {
            Form3 form3 = new Form3();
            form3.Show();
        }

        private void pictureBox3_Click(object sender, EventArgs e)
        {
            Form4 form4 = new Form4();
            form4.Show();
        }

        private void pictureBox4_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            MessageBox.Show("Jocul clasic Simon Spune: " +
                "Jocul creează o serie de tonuri și lumini și cere unui utilizator să repete secvența. Dacă utilizatorul reușește, seria devine progresiv mai lungă și mai complexă. Odată ce utilizatorul greșește jocul s-a terminat."); 
        }

        private void button2_Click(object sender, EventArgs e)
        {
            MessageBox.Show("Jocul Prinde Mere: " +
                "Jucătorul trebuie să prindă merele cu ajutorul coșului. Fiecare măr prins aduce un punct. Dacă un măr este ratat sau o cărămidă este prinsă, jucătorul pierde o viață. Un măr de aur prins recapătă o viață pierdută sau aduce trei puncte dacă jucătorul are toate viețile. Jocul se termină când jucătorul își pierde toate viețile.");
        }
    }
}
